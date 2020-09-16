package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.address.AddressSys;
import cn.huiounet.pojo.cart.CartSys;
import cn.huiounet.pojo.dingyuexiaoxi.Template;
import cn.huiounet.pojo.dingyuexiaoxi.TemplateParam;
import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.order.*;
import cn.huiounet.pojo.shop.ShopSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.math.Arith;
import cn.huiounet.utils.redis.RedisUtil;
import cn.huiounet.utils.tuikuan.TuiKuanSys;
import cn.huiounet.utils.wxPay.WXPayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = Logger.getLogger(AppSysController.class);
    @Autowired
    private OrderSysService orderSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private OrderAddressService orderAddressService;

    @Autowired
    private GoodsSizeService goodsSizeService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ReturnGoodsService returnGoodsService;


    @Autowired
    private GoodsColorService goodsColorService;

    @Autowired
    private AddressSysService addressSysService;

    @Autowired
    private CartSysService cartSysService;

    @Autowired
    private ShopSysService shopSysService;

    @Autowired
    private OrderSellAfterService orderSellAfterService;

    /**
     * 一个
     *
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/save")
    private String create_order(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");

        String shop_id = request.getParameter("shop_id");
        String goods_color = request.getParameter("goods_color");
        String goods_num = request.getParameter("goods_num");
        if(Integer.parseInt(goodsSysService.findId(goods_id).getKucun()) < Integer.parseInt(goods_num) ){
            return "fail";
        }
        String address_id = request.getParameter("address_id");
        String order_lx = request.getParameter("order_lx");
        String goods_size = request.getParameter("goods_size");
        String nonceStr = WXPayUtil.generateUUID(); //订单号

        if(order_lx.equals("2")){
            //直接减少库存
            GoodsSys id = goodsSysService.findId(goods_id);

            String kucun = id.getKucun();

            goodsSysService.updateKuCun(Integer.parseInt(kucun)-Integer.parseInt(goods_num)+"",goods_id);
        }

        GoodsSize byId = goodsSizeService.findById(goods_size);

        GoodsColor byId1 = goodsColorService.findById(goods_color);

        OrderSys orderSys = new OrderSys();
        orderSys.setOrder_lx(order_lx);
        orderSys.setUser_id(user_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderSys.setCreat_time(df.format(new Date()));

        orderSys.setYun_fei(goodsSysService.findId(goods_id).getYun_fei());

        ReturnGoods returnGoods = new ReturnGoods();

        returnGoods.setColor(byId1.getColor());
        returnGoods.setImg(byId1.getImg());
        returnGoods.setName(goodsSysService.findId(goods_id).getGoods_name());
        returnGoods.setOrder_num(nonceStr);
        returnGoods.setNum(goods_num);
        returnGoods.setSize(byId.getSize());
        returnGoods.setPrice(byId.getPrice());
        returnGoods.setGoods_id(byId1.getGoods_id());


        ShopSys byOpenId = shopSysService.findByOpenId(shop_id);


        orderSys.setShop_img(byOpenId.getShop_head_img());
        orderSys.setShop_name(byOpenId.getShop_name());
        orderSys.setOrder_num(nonceStr);
        orderSys.setShop_id(shop_id);

        orderSys.setAddress_num(address_id);
        orderSys.setPay_status("not_pay"); //未支付
        long totalMilliSeconds = System.currentTimeMillis();
        long time = totalMilliSeconds + 300000;
        orderSys.setLast_time(time + "");

        //总价格单位分
        Double price = Double.valueOf(byId.getPrice());
        Double num = Double.valueOf(goods_num);
        double allPrice = Arith.mul(price, num);
        double mul = Arith.mul(allPrice, 100);

        orderSys.setAll_money(new Double(mul).intValue() + "");

        //虽然订单保存了地址i防止删除
        AddressSys byId2 = addressSysService.findById(address_id);
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setOrder_num(nonceStr);
        orderAddress.setUser_address(byId2.getUser_address());
        orderAddress.setUser_name(byId2.getUser_name());
        orderAddress.setUser_phone(byId2.getUser_phone());
        orderAddress.setTip(byId2.getTip());
        orderAddressService.saveOrderAddress(orderAddress);
        orderSysService.saveOrder(orderSys);
        returnGoodsService.saveReturnGoods(returnGoods);

        logger.info("用户Id:"+user_id+"生成商品Id:"+goods_id+"的订单；类型为:"+order_lx+"订单号为:"+nonceStr);
        return nonceStr;
    }



    /**
     * fand
     *
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/saveCartListToOrder")
    private List<String> saveCartList(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String CartidList = request.getParameter("idList");

        String[] split = CartidList.split("\\|");
        List<CartSys> cartSys = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            //思路：根据cartid查找到该购物车资料再进行shopId分组找到不同的shopId进行订单分组
            CartSys byId = cartSysService.findById(split[i]);

            cartSys.add(byId);//存入集合
        }
        Map<String, List<CartSys>> userGroupMap = cartSys.stream().
                collect(Collectors.groupingBy(CartSys::getShop_id));
        List<String> strings = new ArrayList<>();
        for (Map.Entry<String, List<CartSys>> entry : userGroupMap.entrySet()) {
            String nonceStr = WXPayUtil.generateUUID(); //订单号
            String mapKey = entry.getKey(); //mapKey就是shop_id
            List<CartSys> value = entry.getValue(); //对应shop下面的goods
            OrderSys orderSys = new OrderSys();
            orderSys.setUser_id(user_id);
            orderSys.setShop_id(mapKey);
            ShopSys byOpenId = shopSysService.findByOpenId(mapKey);
            orderSys.setShop_name(byOpenId.getShop_name());
            orderSys.setShop_img(byOpenId.getShop_head_img());
            orderSys.setOrder_lx("1");
            orderSys.setPay_status("not_pay");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderSys.setCreat_time(df.format(new Date()));
            orderSys.setOrder_num(nonceStr);


            int yunfeiMoney = 0;
            BigDecimal money = new BigDecimal(0);
            for (int n = 0; n < value.size(); n++) {
                CartSys cartSys1 = value.get(n);
                ReturnGoods returnGoods1 = new ReturnGoods();
                returnGoods1.setOrder_num(nonceStr);
                returnGoods1.setName(cartSys1.getGoods_name());
                returnGoods1.setImg(cartSys1.getImg());
                returnGoods1.setNum(cartSys1.getGoods_num());
                returnGoods1.setPrice(cartSys1.getGoods_price());
                returnGoods1.setGoods_id(cartSys1.getGoods_id());
                returnGoods1.setSize(cartSys1.getSize());
                returnGoods1.setColor(cartSys1.getColor());
                BigDecimal b1 = new BigDecimal(cartSys1.getGoods_price());
                BigDecimal b2 = new BigDecimal(100);
                BigDecimal multiply = b1.multiply(b2);
                logger.info("数量:" + Integer.parseInt(cartSys1.getGoods_num()));
                BigDecimal b3 = new BigDecimal(Integer.parseInt(cartSys1.getGoods_num()));
                BigDecimal b4 = multiply.multiply(b3);
                money = b4.add(money);
                returnGoodsService.saveReturnGoods(returnGoods1);
                String goods_id = cartSys1.getGoods_id();
                GoodsSys id = goodsSysService.findId(goods_id);
                String yun_fei = id.getYun_fei();
                int i = Integer.parseInt(yun_fei);
                yunfeiMoney = i + yunfeiMoney;
            }
            int money_ = money.intValue();

            orderSys.setAll_money(money_ + "");
            long totalMilliSeconds = System.currentTimeMillis();
            long time = totalMilliSeconds + 300000;
            orderSys.setLast_time(time + "");
            strings.add(nonceStr);
            //添加地址
            AddressSys byStatus = addressSysService.findByStatus(user_id, "1");
            String user_name = byStatus.getUser_name();

            OrderAddress orderAddress = new OrderAddress();
            orderAddress.setOrder_num(nonceStr);
            orderAddress.setUser_name(user_name);
            orderAddress.setUser_phone(byStatus.getUser_phone());
            orderAddress.setTip(byStatus.getTip());
            orderAddress.setUser_address(byStatus.getUser_address());
            orderAddressService.saveOrderAddress(orderAddress);
            orderSys.setAddress_num(byStatus.getId() + "");
            orderSys.setYun_fei(yunfeiMoney + "");
            orderSysService.saveOrder(orderSys);

        }

        for (int m = 0; m < split.length; m++) {
            String s = split[m];

            cartSysService.deleteById(s);
        }
        logger.info("用户ID"+user_id+"通过购物车生成订单");
        return strings;
    }


    /**
     * fand
     *
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/find")
    private ReturnOrder _order(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
        String last_time = byOrderNum.getLast_time();
        long time = Long.parseLong(last_time);
        long totalMilliSeconds = System.currentTimeMillis();
        long time2 = time - totalMilliSeconds;
        byOrderNum.setLast_time(time2 + "");

        List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);

        return new ReturnOrder(byOrderNUm, byOrderNum);
    }


    @GetMapping("/findOrderStatus")
    private List<ReturnOrder> findOrderStatus(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String pay_status = request.getParameter("pay_status");
        String start = request.getParameter("start");

        List<OrderSys> orderStatus = orderSysService.findOrderStatus(user_id, pay_status, Integer.parseInt(start), 5);

        List<ReturnOrder> returnOrders = new ArrayList<>();
        for (int i = 0; i < orderStatus.size(); i++) {

            OrderSys orderSys = orderStatus.get(i);
            String order_num = orderSys.getOrder_num();
            List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
            ReturnOrder returnOrder = new ReturnOrder(byOrderNUm, orderSys);
            if (pay_status.equals("not_pay")) {
                String last_time = orderSys.getLast_time();
                long lt = Long.parseLong(last_time);
                long totalMilliSeconds = System.currentTimeMillis();
                if (lt < totalMilliSeconds) {
                    orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
                    continue;
                }
            }
            returnOrders.add(returnOrder);
        }

        return returnOrders;
    }


    @GetMapping("/findAll")
    private List<ReturnOrder> findAll(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String start = request.getParameter("start");

        List<OrderSys> all = orderSysService.findAll(user_id, Integer.parseInt(start), 5);

        List<ReturnOrder> returnOrders = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {

            OrderSys orderSys = all.get(i);
            String order_num = orderSys.getOrder_num();
            List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
            ReturnOrder returnOrder = new ReturnOrder(byOrderNUm, orderSys);
            if (orderSys.getPay_status().equals("not_pay")) {
                String last_time = orderSys.getLast_time();
                long lt = Long.parseLong(last_time);
                long totalMilliSeconds = System.currentTimeMillis();
                if (lt < totalMilliSeconds) {
                    orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
                }
            }
            returnOrders.add(returnOrder);
        }

        return returnOrders;
    }


    @GetMapping("/updateAddress")
    private void updateAddress(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        String address_num = request.getParameter("address_num");

        AddressSys byId = addressSysService.findById(address_num);

        orderAddressService.updateByOrderNum(byId.getTip(), byId.getUser_name(), byId.getUser_phone(), byId.getUser_address(), order_num);
    }


    @GetMapping("/address")
    private OrderAddress address(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        OrderAddress byOrderNum = orderAddressService.findByOrderNum(order_num);

        return byOrderNum;
    }

    @GetMapping("/faHuo")
    private Result faHuo(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        String fa_huo_num = request.getParameter("fa_huo_num");
        if (orderSysService.findByOrderNum(order_num).getPay_status().equals("is_fa_huo")) {
            return Result.fail("已经发货了");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderSysService.updataPayStatusByOrderNum("is_fa_huo", order_num);
        orderSysService.updateFaHuo(df.format(new Date()), fa_huo_num, order_num);

        //订阅消息
        //获取access_token
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口


        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.get("token");

        if (token == null) {
            token = GetTokenUtil.getToken_wx();

            jedis.set("token", token);

        }
        //获得openid
        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
        String user_id = byOrderNum.getUser_id();
        String shop_name = byOrderNum.getShop_name();
        UserInfoSystem byId = userInfoService.findById(user_id);
        OrderAddress byId1 = orderAddressService.findByOrderNum(order_num);
        String open_id = byId.getOpen_id();
        //得到了模板id;zTNpnRfGK8YgL2i6cctWsaIdPXqUHtdk8F60fxT6RGE
        Template template = new Template();
        template.setTemplate_id("_uZI6WYGXVAzKmSymWVPXFEeBWbGXJ2WNC-TfMU-DhI");
        template.setTouser(open_id);
        template.setPage("/pages/orderDetail/orderDetail?order_num=" + order_num);
        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("character_string1", order_num));
        paras.add(new TemplateParam("thing2", shop_name + "的商品发货"));
        paras.add(new TemplateParam("character_string4", fa_huo_num));
        paras.add(new TemplateParam("date5", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        paras.add(new TemplateParam("thing11", byId1.getUser_address()));

        template.setTemplateParamList(paras);

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);


        String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

        logger.info("订单:"+order_num+"发货，订阅消息发送情况:"+reqMess);

        return Result.ok(reqMess);
    }


    @GetMapping("/cancel")
    private void cancel(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
        logger.info("订单号:"+order_num+"被取消");
    }


    @GetMapping("/shouhuo")
    private void shouhuo(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        orderSysService.updataPayStatusByOrderNum("is_shou_huo", order_num);
        logger.info("订单号："+order_num+"收货");
    }

    @GetMapping("/tui_kuan")
    private String tui_kuan(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");


        String order_num = request.getParameter("order_num");
        String token = request.getParameter("token");



        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
        String sysToken = RedisUtil.redisGetString(byOrderNum.getUser_id());

        if(!sysToken.equals(token)){
            return "token_error";
        }

        String nonceStr = WXPayUtil.generateUUID(); //订单号


        if (byOrderNum.getAll_pay() == null) {
            List<OrderSys> payNumList = orderSysService.findPayNumList(byOrderNum.getPay_num());
            int money = 0;
            for (int i = 0; i < payNumList.size(); i++) {
                OrderSys orderSys = payNumList.get(i);
                String all_money = orderSys.getAll_money();
                String yun_fei = orderSys.getYun_fei();
                int i1 = Integer.parseInt(all_money);
                int i2 = Integer.parseInt(yun_fei);
                money = i1 + i2 + money;
            }
            String pay_num = byOrderNum.getPay_num();
            String yun_fei = byOrderNum.getYun_fei();
            String all_money = byOrderNum.getAll_money();
            TuiKuanSys.tuiKuan(pay_num, nonceStr, money, Integer.parseInt(yun_fei) + Integer.parseInt(all_money), "商品退款");
            orderSysService.updateTk("yes", Integer.parseInt(yun_fei) + Integer.parseInt(all_money) + "", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), order_num);
            orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
            logger.info("订单号退款:"+order_num);
            return "ok";
        } else {
            TuiKuanSys.tuiKuan(order_num, nonceStr, Integer.parseInt(byOrderNum.getAll_money()) + Integer.parseInt(byOrderNum.getYun_fei()), Integer.parseInt(byOrderNum.getAll_money()) + Integer.parseInt(byOrderNum.getYun_fei()), "商品退款");
            orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
            orderSysService.updateTk("yes", Integer.parseInt(byOrderNum.getAll_money()) + Integer.parseInt(byOrderNum.getYun_fei()) + "", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), order_num);
            logger.info("订单号退款:"+order_num);
            return "ok";
        }
    }

    @GetMapping("/findOrderNum")
    private ReturnOrderNum findOrderNum(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");

        List<OrderSys> orderNumByUserId = orderSysService.findOrderNumByUserId(user_id);
        for (int i = 0; i < orderNumByUserId.size(); i++) {
            OrderSys orderSys = orderNumByUserId.get(i);
            String last_time = orderSys.getLast_time();
            long lt = Long.parseLong(last_time);
            long totalMilliSeconds = System.currentTimeMillis();
            if (lt < totalMilliSeconds) {
                orderSysService.updataPayStatusByOrderNum("is_cancel", orderSys.getOrder_num());
            }
        }

        int not_pay = orderSysService.findAllNum(user_id, "not_pay");
        int is_fa_huo = orderSysService.findAllNum(user_id, "is_fa_huo");
        int is_payed = orderSysService.findAllNum(user_id, "is_payed");
        int is_cancel = orderSysService.findAllNum(user_id, "is_cancel");

        ReturnOrderNum returnOrderNum = new ReturnOrderNum(orderSysService.findOrderNumByUserId(user_id).size(), is_payed, is_cancel, is_fa_huo);

        return returnOrderNum;

    }


    @GetMapping("/deleteByOrderNum")
    private void deleteByOrderNum(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        orderSysService.deleteByOrderNum(order_num);

        logger.info("订单被删除:"+order_num);
    }


    @GetMapping("/findShByOrderNum")
    private OrderSellAfter findShByOrderNum(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        OrderSellAfter byOrderNum = orderSellAfterService.findByOrderNum(order_num);

        return byOrderNum;
    }

    @GetMapping("/findShByOrderNumHas")
    private Result findShByOrderNumHas(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        OrderSellAfter byOrderNum = orderSellAfterService.findByOrderNum(order_num);
        if (byOrderNum == null) {
            return Result.ok("noHas");
        } else {
            return Result.ok("has");
        }
    }

    @GetMapping("/findShByUserId")
    private List<ReturnSellAfter> findShByUserId(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String start = request.getParameter("start");

        List<OrderSellAfter> byOrderNums = orderSellAfterService.findByUserId(user_id, Integer.parseInt(start), 5);

        List<ReturnSellAfter> returnSellAfters = new ArrayList<>();
        for (int i = 0; i < byOrderNums.size(); i++) {
            OrderSellAfter orderSellAfter = byOrderNums.get(i);
            String order_num = orderSellAfter.getOrder_num();
            OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
            List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
            ReturnSellAfter returnSellAfter = new ReturnSellAfter(byOrderNum, byOrderNUm, orderSellAfter);
            returnSellAfters.add(returnSellAfter);
        }

        return returnSellAfters;
    }


    @GetMapping("/findShByUserIdNum")
    private Result findShByUserIdNum(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");


        int byUserIdNum = orderSellAfterService.findByUserIdNum(user_id);

        return Result.ok(byUserIdNum + "");
    }

    @GetMapping("/updateUserThink")
    private void updateUserThink(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        String user_think = request.getParameter("user_think");

        orderSellAfterService.updateUserThink(user_think, order_num);

    }


    @GetMapping("/SaveSh")
    private Result SaveSh(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String why_tk = request.getParameter("why_tk");
        String order_num = request.getParameter("order_num");
        String say_mess = request.getParameter("say_mess");
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
        OrderSellAfter orderSellAfter = new OrderSellAfter();
        orderSellAfter.setCreate_time(format);
        orderSellAfter.setSay_mess(say_mess);
        orderSellAfter.setOrder_num(order_num);
        orderSellAfter.setUser_id(user_id);
        orderSellAfter.setWhy_tk(why_tk);
        orderSellAfter.setStatus("0");
        orderSellAfter.setTk_money(Integer.parseInt(byOrderNum.getYun_fei()) + Integer.parseInt(byOrderNum.getAll_money()) + "");
        orderSellAfterService.saveSellAfter(orderSellAfter);
        /*订阅消息*/
        //获取access_token
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口


        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.get("token");

        if (token == null) {
            token = GetTokenUtil.getToken_wx();

            jedis.set("token", token);

        }
        //获得openid
        UserInfoSystem byId = userInfoService.findById(user_id);
        String open_id = byId.getOpen_id();
        //得到了模板id;zTNpnRfGK8YgL2i6cctWsaIdPXqUHtdk8F60fxT6RGE
        Template template = new Template();
        template.setTemplate_id("350YLkwydqr47t1ZgtnjLdpYVtEBjnjY7u0ft5dr3lQ");
        template.setTouser(open_id);
        template.setPage("/pages/refundList/refundList?user_id=" + user_id);
        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("character_string1", order_num));
        //(Integer.parseInt(byOrderNum.getYun_fei()) + Integer.parseInt(byOrderNum.getAll_money()) / 100)
        paras.add(new TemplateParam("amount2", (Double.valueOf(byOrderNum.getYun_fei()) + Double.valueOf(byOrderNum.getAll_money())) / 100 + ""));
        paras.add(new TemplateParam("thing4", why_tk));
        paras.add(new TemplateParam("thing5", say_mess));
        template.setTemplateParamList(paras);

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);


        String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

        logger.info("用户Id"+user_id+"申请售后，订阅消息状态:"+reqMess);

        return Result.ok(reqMess);

    }

    @GetMapping("/updateSh")
    private Result updateSh(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String order_num = request.getParameter("order_num");
        String status = request.getParameter("status");
        String beizhu = request.getParameter("beizhu");
        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);


        String user_id = byOrderNum.getUser_id();

        /*订阅消息*/
        //获取access_token
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口


        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.get("token");

        if (token == null) {
            token = GetTokenUtil.getToken_wx();

            jedis.set("token", token);

        }
        //获得openid
        UserInfoSystem byId = userInfoService.findById(user_id);
        String open_id = byId.getOpen_id();
        //得到了模板id;zTNpnRfGK8YgL2i6cctWsaIdPXqUHtdk8F60fxT6RGE
        Template template = new Template();
        template.setTemplate_id("OQB_nYB48Lr6u5rNjgijQWNQxH8lDgeyKu6sjiPWmeA");
        template.setTouser(open_id);
        template.setPage("/pages/refundList/refundList?user_id=" + user_id);
        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("character_string1", order_num));
        paras.add(new TemplateParam("time3", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        String ShenHe = "";
        if (status.equals("1")) {
            ShenHe = "审核通过";
            //退款
            String nonceStr = WXPayUtil.generateUUID(); //订单号


            if (byOrderNum.getAll_pay() == null) {
                List<OrderSys> payNumList = orderSysService.findPayNumList(byOrderNum.getPay_num());
                int money = 0;
                for (int i = 0; i < payNumList.size(); i++) {
                    OrderSys orderSys = payNumList.get(i);
                    String all_money = orderSys.getAll_money();
                    String yun_fei = orderSys.getYun_fei();
                    int i1 = Integer.parseInt(all_money);
                    int i2 = Integer.parseInt(yun_fei);
                    money = i1 + i2 + money;
                }
                String pay_num = byOrderNum.getPay_num();
                String yun_fei = byOrderNum.getYun_fei();
                String all_money = byOrderNum.getAll_money();
                TuiKuanSys.tuiKuan(pay_num, nonceStr, money, Integer.parseInt(yun_fei) + Integer.parseInt(all_money), "商品退款");
                orderSysService.updateTk("yes", Integer.parseInt(yun_fei) + Integer.parseInt(all_money) + "", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), order_num);
                orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
            } else {
                TuiKuanSys.tuiKuan(order_num, nonceStr, Integer.parseInt(byOrderNum.getAll_money()) + Integer.parseInt(byOrderNum.getYun_fei()), Integer.parseInt(byOrderNum.getAll_money()) + Integer.parseInt(byOrderNum.getYun_fei()), "商品退款");
                orderSysService.updataPayStatusByOrderNum("is_cancel", order_num);
                orderSysService.updateTk("yes", Integer.parseInt(byOrderNum.getAll_money()) + Integer.parseInt(byOrderNum.getYun_fei()) + "", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), order_num);
            }
        } else {
            ShenHe = "审核不通过";
        }
        paras.add(new TemplateParam("thing4", ShenHe));
        paras.add(new TemplateParam("thing2", beizhu));

        template.setTemplateParamList(paras);

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);


        String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

        logger.info(reqMess);
        orderSellAfterService.updateStatus(status, beizhu, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), order_num);

        logger.info("订单"+order_num+"售后审核:"+ShenHe);
        return Result.ok(reqMess);

    }


}

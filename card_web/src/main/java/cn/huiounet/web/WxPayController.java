package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import cn.huiounet.pojo.order.CzOrder;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.pojo.order.ZhuanZhangOrder;
import cn.huiounet.pojo.vip.Vip;
import cn.huiounet.pojo.vip.VipLog;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.create_order.CreateOrder;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.math.Arith;
import cn.huiounet.utils.wxPay.WXPayUtil;
import cn.huiounet.utils.xml.XmlPayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class WxPayController {

    //日志服务
    private static final Logger logger = Logger.getLogger(WxPayController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ZhuanZhangOrderService zhuanZhangOrderService;

    @Autowired
    private HuaFeiService huaFeiService;

    @Autowired
    private OrderSysService orderSysService;

    @Autowired
    private CouponSysService couponSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private CzOrderService czOrderService;

    @Autowired
    private VipLogService vipLogService;

    @Autowired
    private VipService vipService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @Autowired
    private HuaFeiOrderSysService huaFeiOrderSysService;

    @GetMapping("/order")
    public Map<String, String> createOrder(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String order_num = request.getParameter("order_num");


        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);

        String payStatus = byOrderNum.getPay_status();

        if (payStatus.equals("not_pay")) {

            String shop_name = byOrderNum.getShop_name();


            UserInfoSystem byId = userInfoService.findById(user_id);


            int AllMoney = Integer.parseInt(byOrderNum.getAll_money());

            String yun_fei = byOrderNum.getYun_fei();
            AllMoney  = AllMoney + Integer.parseInt(yun_fei);
            if(byOrderNum.getYouhui_status() != null){
                if(byOrderNum.getYouhui_status().equals("1")){
                    String youhui_much = byOrderNum.getYouhui_much();
                    double mul = Arith.mul(Double.parseDouble(youhui_much), Double.valueOf(100));
//                    int i1 = Integer.parseInt(youhui_much) * 100;
                    int i = new Double(mul).intValue();
                    AllMoney = AllMoney - i;
                }
            }
            Map<String, String> payResult = CreateOrder.createOrder(shop_name + "-商品购买", byId.getOpen_id(), order_num, AllMoney);

            logger.info(payResult);

            return payResult;
        } else {
            return null;
        }
    }


    @GetMapping("/orderList")
    public Map<String, String> createOrderList(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");

        String order_num = request.getParameter("order_num");

        String[] split = order_num.split("\\|");
        int AllMoney = 0;
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s == "") {
                continue;
            }
            OrderSys byOrderNum = orderSysService.findByOrderNum(s);
            if (byOrderNum.getPay_status().equals("is_payed")) {
                continue;
            }
            String all_money = byOrderNum.getAll_money();
            String yun_fei = byOrderNum.getYun_fei();
            AllMoney = Integer.parseInt(all_money) + Integer.parseInt(yun_fei) + AllMoney;
        }

        if (AllMoney == 0) {
            return null;
        }

        UserInfoSystem byId = userInfoService.findById(user_id);


        Map<String, String> payResult = CreateOrder.createOrder("商品购买", byId.getOpen_id(), split[0].substring(0,32), AllMoney);

        logger.info(payResult);

        return payResult;

    }

    //支付查询
    @GetMapping("/pay_status")
    public Result payStatus(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String order_num = request.getParameter("order_num");

        String notic = request.getParameter("notic");


        String pay_status = XmlPayUtil.psyStatusXml(order_num);

        String mess = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/orderquery", pay_status);
        String trade_state = "";
        try {
            Map<String, String> map = WXPayUtil.xmlToMap(mess);
            trade_state = map.get("trade_state");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("错误" + e);
        }
        if (trade_state.equals("SUCCESS")) {
            if (!notic.equals("")) {
                orderSysService.updateNotic(notic, order_num);
            }
            logger.info("微信支付订单号："+order_num+"支付成功");
            return Result.ok("ok"); //支付成功
        } else {
            logger.info("微信支付订单号："+order_num+"支付失败");
            return Result.ok("fail");//支付失败
        }
    }

    //支付查询
    @GetMapping("/pay_statusList")
    public Result payStatusList(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String order_num = request.getParameter("order_num");

        String notic = request.getParameter("notic");
        String[] split = order_num.split("\\|");

        String pay_status = XmlPayUtil.psyStatusXml(split[0].substring(0,32));

        String mess = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/orderquery", pay_status);
        String trade_state = "";
        try {
            Map<String, String> map = WXPayUtil.xmlToMap(mess);
            trade_state = map.get("trade_state");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("错误" + e);
        }
        if (trade_state.equals("SUCCESS")) {
            //已支付
            for (int n = 0; n < split.length; n++) {
//                orderSysService.updataPayStatusByOrderNum("is_payed", split[n]);
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                orderSysService.updataPayTime(df.format(new Date()), order_num);
//                List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(split[n]);
//                for (int i = 0; i < byOrderNUm.size(); i++) {
//                    ReturnGoods returnGoods = byOrderNUm.get(i);
//                    String goods_id = returnGoods.getGoods_id();
//                    GoodsSys id = goodsSysService.findId(goods_id);
//                    String sell_many = id.getSell_many();
//                    String kucun = id.getKucun();
//                    int i1 = Integer.parseInt(sell_many);
//                    int i2 = Integer.parseInt(kucun);
//                    int sellMany = i1 + 1;
//                    String num = returnGoods.getNum();
//                    int i3 = i2 - Integer.parseInt(num); //剩余酷讯
//                    goodsSysService.updateSell_many(sellMany + "", goods_id);
//                    goodsSysService.updateKuCun(i3+"",goods_id);
//                }
                if (!notic.equals("")) {
                    orderSysService.updateNotic(notic, split[n]);
                }
//                orderSysService.updatePayNumById(split[0].substring(0,12),orderSysService.findByOrderNum(split[n]).getId()+"");
            }
            logger.info("微信支付订单号："+order_num+"支付成功");
            return Result.ok("ok"); //支付成功
        } else {
            logger.info("微信支付订单号："+order_num+"支付失败");
            return Result.ok("fail");//支付失败
        }
    }

    @PostMapping("/callBack")
    public void callBack(HttpServletRequest request ,HttpServletResponse response)throws Exception{
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultxml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> map = WXPayUtil.xmlToMap(resultxml);


        logger.info("商户订单号："+map.get("out_trade_no"));
        if(map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
            response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
            String out_trade_no = map.get("out_trade_no");
            /**
             * 订单选择项
             */
            if(orderSysService.findByOrderNum(out_trade_no) != null){

                //商品订单
                //已支付
                String order_num = out_trade_no;
                if(orderSysService.findByOrderNum(out_trade_no).getIs_zh().equals("1")){
                    String zh_order_num = orderSysService.findByOrderNum(out_trade_no).getZh_order_num();
                    List<OrderSys> zhOrderNum = orderSysService.findZhOrderNum(zh_order_num);
                    for (int n = 0; n < zhOrderNum.size(); n++) {
                        orderSysService.updatePayWay("wxPay",zhOrderNum.get(n).getOrder_num());
                        orderSysService.updataPayStatusByOrderNum("is_payed", zhOrderNum.get(n).getOrder_num());
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        orderSysService.updataPayTime(df.format(new Date()), order_num);
                        List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(zhOrderNum.get(n).getOrder_num());
                        int jifen = 0;
                        for (int i = 0; i < byOrderNUm.size(); i++) {
                            ReturnGoods returnGoods = byOrderNUm.get(i);
                            String goods_id = returnGoods.getGoods_id();
                            GoodsSys id = goodsSysService.findId(goods_id);
                            String sell_many = id.getSell_many();
                            String kucun = id.getKucun();
                            int i1 = Integer.parseInt(sell_many);
                            int i2 = Integer.parseInt(kucun);
                            int sellMany = i1 + 1;
                            String num = returnGoods.getNum();
                            int i3 = i2 - Integer.parseInt(num); //剩余酷讯
                            if(id.getIs_add_jifen() != null){
                                if (id.getJi_fen() != null){
                                    jifen = jifen + Integer.parseInt(id.getJi_fen());
                                }
                            }
                            goodsSysService.updateSell_many(sellMany + "", goods_id);
                            goodsSysService.updateKuCun(i3+"",goods_id);
                        }
                        UserInfoSystem byId = userInfoService.findById(orderSysService.findByOrderNum(zhOrderNum.get(n).getOrder_num()).getUser_id());
                        String jifen1 = byId.getJifen();
                        int i = Integer.parseInt(jifen1) + jifen;
                        userInfoService.updateJiFen(i+"",orderSysService.findByOrderNum(zhOrderNum.get(n).getOrder_num()).getUser_id());
                        orderSysService.updatePayNumById(order_num,orderSysService.findByOrderNum(zhOrderNum.get(n).getOrder_num())+"");
                    }
                }else {
                    List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
                    int jifen = 0;
                    for (int i = 0; i < byOrderNUm.size(); i++) {
                        ReturnGoods returnGoods = byOrderNUm.get(i);
                        String goods_id = returnGoods.getGoods_id();
                        GoodsSys id = goodsSysService.findId(goods_id);
                        String sell_many = id.getSell_many();
                        String kucun = id.getKucun();
                        int i2 = Integer.parseInt(kucun);
                        int i1 = Integer.parseInt(sell_many);
                        int sellMany = i1 + 1;
                        String num = returnGoods.getNum();
                        int i3 = i2 - Integer.parseInt(num); //剩余库存
                        if(id.getIs_add_jifen() != null){
                            if (id.getJi_fen() != null){
                                jifen = jifen + Integer.parseInt(id.getJi_fen());
                            }
                        }
                        goodsSysService.updateSell_many(sellMany + "", goods_id);
                        goodsSysService.updateKuCun(i3 + "", goods_id);
                    }
                    OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
                    UserInfoSystem byId = userInfoService.findById(byOrderNum.getUser_id());
                    String jifen1 = byId.getJifen();
                    int i = Integer.parseInt(jifen1) + jifen;
                    userInfoService.updateJiFen(i+"",byOrderNum.getUser_id()+"");
                    if (byOrderNum.getYouhuiquan_id() != null) {
                        couponSysService.updateById("2", byOrderNum.getYouhuiquan_id());
                    }
                    orderSysService.updataPayStatusByOrderNum("is_payed", order_num);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    orderSysService.updataPayTime(df.format(new Date()), order_num);
                    orderSysService.updatePayWay("wxPay", order_num);
                    orderSysService.updatePayNumById(order_num, orderSysService.findByOrderNum(order_num).getId() + "");

                    orderSysService.updateAll_pay("1", order_num);
                }
            }else  if(czOrderService.findByOrderNum(out_trade_no) != null){
                //充值订单
                //已支付
                String order_num = out_trade_no;
                CzOrder byOrderNum = czOrderService.findByOrderNum(order_num);
                UserInfoSystem byId = userInfoService.findById(byOrderNum.getUser_id());
                String money = byId.getMoney();
                double v = Double.parseDouble(money);
                String cz_money = byOrderNum.getCz_money();
                double v1 = Double.parseDouble(cz_money);
                double add = Arith.add(v, v1);
                userInfoService.updateMoney(add+"",byOrderNum.getUser_id());
            }else if(vipLogService.findByOrderNum(out_trade_no) != null){
                //vip充值订单
                //已支付
                String order_num = out_trade_no;
                VipLog byOrderNum = vipLogService.findByOrderNum(order_num);
                String user_id = byOrderNum.getUser_id();
                vipLogService.updateByOrderNum("pay",order_num);
                Vip byId1 = vipService.findById("1");
                String time = byId1.getTime();
                UserInfoSystem byId = userInfoService.findById(user_id);
                if(byId.getIs_vip().equals("0")){
                    //没有
                    long l = System.currentTimeMillis();
                    Long time2 = 86400000L * Integer.parseInt(time);
                    long viptime = l+time2;
                    Date date = new Date();
                    date.setTime(viptime);
                    String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    userInfoService.updateVipTime(format,user_id);
                }else {
                    String vip_time = byId.getVip_time();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    long dateToSecond = 0;
                    try {
                        dateToSecond = sdf.parse(vip_time).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Long time2 = 86400000L * Integer.parseInt(time);
                    long l = dateToSecond + time2;
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(l);
                    String format = formatter.format(calendar.getTime());
                    userInfoService.updateVipTime(format,user_id);
                }
            }else if(huaFeiOrderSysService.findByOrderNum(out_trade_no) != null){
                //支付成功
                HuaFeiOrderSys byOrderNum = huaFeiOrderSysService.findByOrderNum(out_trade_no);
                String cz_much = byOrderNum.getCz_much();
                String cz_phone = byOrderNum.getCz_phone();
                String s = huaFeiService.JuHeHuaFei(cz_phone, cz_much,out_trade_no);
                logger.info("充值状态："+s);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                huaFeiOrderSysService.updateByOrderNum("is_pay",df.format(new Date()),byOrderNum.getOrder_num());
            }else if(zhuanZhangOrderService.findByOrderNum(out_trade_no) != null){
                zhuanZhangOrderService.updateByOrderNum("is_payed",out_trade_no);
                ZhuanZhangOrder byOrderNum = zhuanZhangOrderService.findByOrderNum(out_trade_no);
                String to_user = byOrderNum.getTo_user();
                UserInfoSystem byId = userInfoService.findById(to_user);
                String money = byId.getMoney();
                Double aDouble = Double.valueOf(money);
                double div = Double.valueOf(byOrderNum.getMoney_num()) / 100;
                double add = Arith.add(aDouble, div);
                userInfoService.updateMoney(add+"",to_user);
            }
            logger.info("异步支付成功");
        }

    }


}

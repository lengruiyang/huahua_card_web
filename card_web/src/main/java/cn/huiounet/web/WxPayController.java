package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.order.OrderSys;


import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.pojo.order.ReturnOrder;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.create_order.CreateOrder;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.wxPay.WXPayUtil;
import cn.huiounet.utils.xml.XmlPayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
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
    private OrderSysService orderSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @GetMapping("/order")
    public Map<String,String> createOrder(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String order_num = request.getParameter("order_num");


        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);

        String payStatus = byOrderNum.getPay_status();

        if(payStatus.equals("not_pay")){

//            if(Long.parseLong(byOrderNum.getLast_time())>System.currentTimeMillis()){
//                return null;
//            }
            String shop_name = byOrderNum.getShop_name();


            UserInfoSystem byId = userInfoService.findById(user_id);


            String all_money = byOrderNum.getAll_money();

            String yun_fei = byOrderNum.getYun_fei();


            Map<String, String> payResult = CreateOrder.createOrder(shop_name+"-商品购买", byId.getOpen_id(), order_num, Integer.parseInt(all_money)+Integer.parseInt(yun_fei));

            logger.info(payResult);

            return payResult;
        }else {
            return null;
        }
    }

    //支付查询
    @GetMapping("/pay_status")
    public Result payStatus(HttpServletResponse response, HttpServletRequest request){
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
            //已支付
            List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
            for(int i = 0;i<byOrderNUm.size();i++){
                ReturnGoods returnGoods = byOrderNUm.get(i);
                String goods_id = returnGoods.getGoods_id();
                GoodsSys id = goodsSysService.findId(goods_id);
                String sell_many = id.getSell_many();
                int i1 = Integer.parseInt(sell_many);
                int sellMany = i1 + 1;
                goodsSysService.updateSell_many(sellMany+"",goods_id);
            }
            orderSysService.updataPayStatusByOrderNum("is_payed",order_num);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderSysService.updataPayTime(df.format(new Date()),order_num);
            if(!notic.equals("")){
                orderSysService.updateNotic(notic,order_num);
            }
            return Result.ok("ok"); //支付成功
        }else {
            return Result.ok("fail");//支付失败
        }
    }


//    @GetMapping("/wx_tk")
//    public String wx_tk(HttpServletResponse response, HttpServletRequest request){
//        response.setContentType("text/html;charset=utf-8");
//        /*设置响应头允许ajax跨域访问*/
//        response.setHeader("Access-Control-Allow-Origin", "*");
//
//        /* 星号表示所有的异域请求都可以接受， */
//        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
//
//        String order_num = request.getParameter("order_num");
//
//
//    }
}

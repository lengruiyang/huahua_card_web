package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.order.CzOrder;
import cn.huiounet.pojo.order.ReturnCz;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.CzOrderService;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.utils.create_order.CreateOrder;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.math.Arith;
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
import java.util.Map;

@RequestMapping("/cz")
@RestController
public class CzMoneyController  {

    private static final Logger logger = Logger.getLogger(CzMoneyController.class);

    @Autowired
    private CzOrderService czOrderService;

    @Autowired
    private UserInfoService userInfoService;



    @GetMapping("/order")
    public ReturnCz createOrder(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String money = request.getParameter("money");


        UserInfoSystem byId = userInfoService.findById(user_id);


        String nonceStr = WXPayUtil.generateUUID(); //订单号

        double v = Double.parseDouble(money);

        double mul = Arith.mul(v, Double.valueOf(100));

        int i = new Double(mul).intValue();

        Map<String, String> payResult = CreateOrder.createOrder( "小欧花花卡-充值", byId.getOpen_id(), nonceStr,i );

        CzOrder czOrder = new CzOrder();

        czOrder.setCz_money(money);
        czOrder.setUser_id(user_id);
        czOrder.setStatus("not_pay");
        czOrder.setOrder_num(nonceStr);
        czOrder.setPay_way("weChat");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        czOrder.setCreate_time(df.format(new Date()));

        czOrderService.savePay(czOrder);

        ReturnCz returnCz = new ReturnCz(payResult,nonceStr);

        return returnCz;
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
            CzOrder byOrderNum = czOrderService.findByOrderNum(order_num);
            UserInfoSystem byId = userInfoService.findById(byOrderNum.getUser_id());
            String money = byId.getMoney();
            double v = Double.parseDouble(money);
            String cz_money = byOrderNum.getCz_money();
            double v1 = Double.parseDouble(cz_money);
            double add = Arith.add(v, v1);
            userInfoService.updateMoney(add+"",byOrderNum.getUser_id());
            logger.info("微信支付订单号："+order_num+"支付成功");
            return Result.ok("ok");
        } else {
            logger.info("微信支付订单号："+order_num+"支付失败");
            return Result.ok("fail");//支付失败
        }
    }

}

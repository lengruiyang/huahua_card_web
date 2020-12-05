package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.order.CzOrder;
import cn.huiounet.pojo.order.ReturnCz;
import cn.huiounet.pojo.order.ZhuanZhangOrder;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.service.ZhuanZhangOrderService;
import cn.huiounet.utils.create_order.CreateOrder;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.math.Arith;
import cn.huiounet.utils.qrcode.GetImage;
import cn.huiounet.utils.qrcode.QRCodeUtil;
import cn.huiounet.utils.wxPay.WXPayUtil;
import cn.huiounet.utils.xml.XmlPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/qr_pay")
public class PayController {
    private static String fileUrl = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/";

    @Autowired
    private ZhuanZhangOrderService zhuanZhangOrderService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("getQrPayImg")
    public String getQrCode(String text) throws Exception{

        if (text == null) {
            return "error:please enter text";
        }

        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        QRCodeUtil.encode(text, fileUrl + format + ".jpg");

        return "https://xcx2.huiounet.cn/imgs/" + format + ".jpg";

    }



    @GetMapping("/order")
    public ReturnCz createOrder(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("from_user");
        String to_user = request.getParameter("to_user");
        String money = request.getParameter("money");


        UserInfoSystem byId = userInfoService.findById(user_id);


        String nonceStr = WXPayUtil.generateUUID(); //订单号

        double v = Double.parseDouble(money);

        double mul = Arith.mul(v, Double.valueOf(100));

        int i = new Double(mul).intValue();

        Map<String, String> payResult = CreateOrder.createOrder( "小欧花花卡-转账", byId.getOpen_id(), nonceStr,i );

        ZhuanZhangOrder zhuanZhangOrder = new ZhuanZhangOrder();
        zhuanZhangOrder.setFrom_user(user_id);
        zhuanZhangOrder.setTo_user(to_user);
        zhuanZhangOrder.setMoney_num(i+"");
        zhuanZhangOrder.setOrder_num(nonceStr);
        zhuanZhangOrder.setStatus("not_pay");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        zhuanZhangOrder.setCreate_time(df.format(new Date()));

        zhuanZhangOrderService.saveOrder(zhuanZhangOrder);

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
        }
        if (trade_state.equals("SUCCESS")) {
            return Result.ok("ok");
        } else {
            return Result.ok("fail");//支付失败
        }
    }


}

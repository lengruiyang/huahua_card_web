package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.order.ReturnCz;
import cn.huiounet.pojo.vip.Vip;
import cn.huiounet.pojo.vip.VipLog;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.service.VipLogService;
import cn.huiounet.service.VipService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("vip")
public class VipController {
    private static final Logger logger = Logger.getLogger(VipController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private VipService vipService;
    @Autowired
    private VipLogService vipLogService;

    @GetMapping("find")
    public Vip find(){

        Vip byId = vipService.findById("1");

        return byId;
    }

    @GetMapping("order")
    public ReturnCz createOrder(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        UserInfoSystem byId = userInfoService.findById(user_id);
        Vip byId1 = vipService.findById("1");
        String price = byId1.getPrice();
        double mul = Arith.mul(Double.parseDouble(price), 100);
        int i = new Double(mul).intValue();
        String nonceStr = WXPayUtil.generateUUID(); //订单号
        Map<String, String> payResult = CreateOrder.createOrder( "小欧花花卡-开通会员", byId.getOpen_id(), nonceStr,i );
        VipLog vipLog = new VipLog();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vipLog.setCreate_time(df.format(new Date()));
        vipLog.setOrder_num(nonceStr);
        vipLog.setStatus("not_pay");
        vipLog.setUser_id(user_id);
        vipLogService.saveVipLog(vipLog);
        ReturnCz returnCz = new ReturnCz(payResult,nonceStr);
        return returnCz;
    }

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


            logger.info("微信支付订单号："+order_num+"支付成功");
            return Result.ok("ok");
        } else {
            logger.info("微信支付订单号："+order_num+"支付失败");
            return Result.ok("fail");//支付失败
        }
    }
}

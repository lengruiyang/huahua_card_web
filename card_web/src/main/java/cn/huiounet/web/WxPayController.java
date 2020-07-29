package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.order.OrderSys;

import cn.huiounet.pojo.utils.HttpRequest;
import cn.huiounet.service.*;
import cn.huiounet.utils.create_order.CreateOrder;
import cn.huiounet.utils.xml.XmlPayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        String goods_id = byOrderNum.getGoods_id();

        GoodsSys Goods = goodsSysService.findId(goods_id);

        UserInfoSystem byId = userInfoService.findById(user_id);

        String goods_name = "";
        if(Goods.getGoods_name().length()>5){
            goods_name = Goods.getGoods_name().substring(0, 5);
            goods_name = goods_name + "....";
        }else {
            goods_name = Goods.getGoods_name();
        }


        String all_money = byOrderNum.getAll_money();


        Map<String, String> payResult = CreateOrder.createOrder(goods_name, byId.getOpen_id(), order_num, Integer.parseInt(all_money));

        logger.info(payResult);

        return payResult;
    }

    @GetMapping("/wx_payMent")
    public String wx_payMent(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

        UserInfoSystem byId = userInfoService.findById(user_id);

        String open_id = byId.getOpen_id();

        //微信企业付款到零钱
        String Mess = XmlPayUtil.QiYeFu_kuan("定单号", open_id, 100, "test");
        try {
            HttpRequest.doRefund("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers",Mess);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

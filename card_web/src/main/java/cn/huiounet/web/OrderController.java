package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.pojo.order.ReturnOrder;
import cn.huiounet.service.*;
import cn.huiounet.utils.math.Arith;
import cn.huiounet.utils.wxPay.WXPayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = Logger.getLogger(AppSysController.class);
    @Autowired
    private OrderSysService orderSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private GoodsSizeService goodsSizeService;

    @Autowired
    private ReturnGoodsService returnGoodsService;


    @Autowired
    private GoodsColorService goodsColorService;

    /**
     * 一个
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/save")
    private String create_order(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");
        String goods_color = request.getParameter("goods_color");
        String goods_num = request.getParameter("goods_num");
        String address_id = request.getParameter("address_id");
        String goods_size = request.getParameter("goods_size");
        String nonceStr= WXPayUtil.generateUUID(); //订单号

        GoodsSize byId = goodsSizeService.findById(goods_size);
        GoodsColor byId1 = goodsColorService.findById(goods_color);
        OrderSys orderSys = new OrderSys();
        orderSys.setUser_id(user_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderSys.setCreat_time(df.format(new Date()));

        ReturnGoods returnGoods = new ReturnGoods();

        returnGoods.setColor(byId1.getColor());
        returnGoods.setImg(byId1.getImg());
        returnGoods.setName(goodsSysService.findId(goods_id).getGoods_name());
        returnGoods.setOrder_num(nonceStr);
        returnGoods.setNum(goods_num);
        returnGoods.setSize(byId.getSize());
        returnGoods.setPrice(byId.getPrice());



        orderSys.setOrder_num(nonceStr);
        orderSys.setGoods_id(goods_id);
        orderSys.setAddress_num(address_id);
        orderSys.setPay_status("not_pay"); //未支付

        //总价格单位分
        Double price = Double.valueOf(byId.getPrice());
        Double num = Double.valueOf(goods_num);
        double allPrice = Arith.mul(price, num);
        double mul = Arith.mul(allPrice, 100);

        orderSys.setAll_money(new Double(mul).intValue()+"");

        orderSysService.saveOrder(orderSys);
        returnGoodsService.saveReturnGoods(returnGoods);

        return nonceStr;
    }


    /**
     * fand
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/find")
    private ReturnOrder _order(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
        List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);

        return new ReturnOrder(byOrderNUm,byOrderNum);
    }
}

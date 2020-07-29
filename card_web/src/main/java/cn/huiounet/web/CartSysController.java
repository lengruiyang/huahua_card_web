package cn.huiounet.web;

import cn.huiounet.pojo.cart.CartSys;
import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.CartSysService;
import cn.huiounet.service.GoodsColorService;
import cn.huiounet.service.GoodsSizeService;
import cn.huiounet.service.GoodsSysService;
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

@RestController
@RequestMapping("/cart")
public class CartSysController {
    private static final Logger logger = Logger.getLogger(CartSysController.class);
    @Autowired
    private CartSysService cartSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private GoodsColorService goodsColorService;

    @Autowired
    private GoodsSizeService goodsSizeService;

    @GetMapping("/save")
    public Result saveCart(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        CartSys cartSys = new CartSys();

        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");
        String color_id = request.getParameter("color_id");
        String about_mess = request.getParameter("about_mess");
        String size_id = request.getParameter("size_id");
        String num = request.getParameter("num");
        GoodsColor goodsColor = goodsColorService.findById(color_id);
        String img = goodsColor.getImg();
        String color = goodsColor.getColor();
        GoodsSize goodsSize = goodsSizeService.findById(size_id);
        String size = goodsSize.getSize();
        String price = goodsSize.getPrice();
        String company = goodsSize.getCompany();
        String mess = size+company;


        GoodsSys goodsSys = goodsSysService.findId(goods_id);
        String goods_name = goodsSys.getGoods_name();

        cartSys.setColor(color);
        cartSys.setGoods_name(goods_name);
        cartSys.setGoods_id(goods_id);
        cartSys.setGoods_price(price);
        cartSys.setGoods_num(num);
        cartSys.setImg(img);
        cartSys.setColor(color);
        cartSys.setSize(mess);
        cartSys.setUser_id(user_id);
        cartSys.setAbout_mess(about_mess);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cartSys.setCreate_time(df.format(new Date()));

        logger.info("保存购物车"+cartSys.toString());

        cartSysService.saveCartSys(cartSys);

        return Result.ok("ok");
    }

    @GetMapping("/delete")
    public Result deleteById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        cartSysService.deleteById(id);

        return Result.ok("ok");
    }


    @GetMapping("/findAll")
    public List<CartSys> findAll(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");

        List<CartSys> byUser_id = cartSysService.findByUser_id(user_id);

        return byUser_id;
    }
}

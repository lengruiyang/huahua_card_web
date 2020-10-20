package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.coupon.CouPon;
import cn.huiounet.pojo.coupon.CouponSys;
import cn.huiounet.pojo.coupon.ReturnCoupon;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.math.Arith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponSysController {
    private static final Logger logger = Logger.getLogger(CouponSysController.class);
    @Autowired
    private CouponSysService couponSysService;

    @Autowired
    private CouPonService couPonService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @Autowired
    private OrderSysService orderSysService;

    @GetMapping("/notChoose")
    public void notChoose(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String order_num = request.getParameter("order_num");

        //初始化
        orderSysService.updateYouHui(null,null,null,order_num);
    }

    @GetMapping("/chooseCoupon")
    public Result chooseCoupon(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String coupon_id = request.getParameter("coupon_id");
        String order_num = request.getParameter("order_num");

        CouponSys byId = couponSysService.findById(coupon_id);
        String price = byId.getPrice();//优惠价
        if(byId.getWhere_from().equals("3")){
            double yh = 0;
            List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
            for(int i = 0;i<byOrderNUm.size();i++){
                ReturnGoods returnGoods = byOrderNUm.get(i);
                String price1 = returnGoods.getPrice();
                double v = Double.parseDouble(price1);
                yh = Arith.add(yh, v);
                //总额
            }
            if(yh > Double.parseDouble(price)){
                orderSysService.updateYouHui("1",price,coupon_id,order_num);
                return Result.ok("ok");
            }else {
                return Result.ok("isNotEnough");
            }
        }

        OrderSys byOrderNum = orderSysService.findByOrderNum(order_num);
        String all_money = byOrderNum.getAll_money();
        double mul = Arith.mul(Double.parseDouble(price), Double.valueOf(100));
//        double i = Double.parseDouble(price) * 100;
        if(Double.parseDouble(all_money) >= mul){
            orderSysService.updateYouHui("1",price,coupon_id,order_num);
            return Result.ok("ok");
        }else {
            return Result.ok("isNotEnough");
        }
    }

    @GetMapping("/findAll")
    public List<CouponSys> findAll(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");
        String status = request.getParameter("status");

        List<CouponSys> byUser = couponSysService.findByUser(user_id,status);

        return byUser;
    }

    @GetMapping("/use_coupon")
    public List<CouponSys> use_coupon(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");

        List<CouponSys> couPonUse = couponSysService.findCouPonUse(user_id, goods_id);

        return couPonUse;
    }

    @GetMapping("/use_coupon_shop")
    public List<CouponSys> use_coupon_shop(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        List<CouponSys> couPonUseShop = couponSysService.findCouPonUseShop(user_id, shop_id);

        return couPonUseShop;
    }

    @GetMapping("/findAllByShop")
    public List<ReturnCoupon> findAllByShop(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String shop_id = request.getParameter("shop_id");
        String user_id = request.getParameter("user_id");
        List<ReturnCoupon> returnCoupons = new ArrayList<>();
        List<CouPon> bySys  = couPonService.findBySysShop(shop_id);
        for(int i = 0;i<bySys.size();i++){
            ReturnCoupon returnCoupon = new ReturnCoupon();
            CouPon couPon = bySys.get(i);
            int id = couPon.getId();
            CouponSys byIdAndUser = couponSysService.findByIdAndUser(id+"", user_id);
            if(byIdAndUser == null){
                returnCoupon.setIs_get("no");
            }else {
                returnCoupon.setIs_get("yes");
            }
            returnCoupon.setCouPon(couPon);
            returnCoupons.add(returnCoupon);
        }

        return returnCoupons;
    }


    @GetMapping("/findAllByGoods")
    public List<ReturnCoupon> findAllByGoods(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        String user_id = request.getParameter("user_id");
        List<ReturnCoupon> returnCoupons = new ArrayList<>();
        List<CouPon> bySys  = couPonService.findBySysGoods(goods_id);
        for(int i = 0;i<bySys.size();i++){
            ReturnCoupon returnCoupon = new ReturnCoupon();
            CouPon couPon = bySys.get(i);
            int id = couPon.getId();
            CouponSys byIdAndUser = couponSysService.findByIdAndUser(id+"", user_id);
            if(byIdAndUser == null){
                returnCoupon.setIs_get("no");
            }else {
                returnCoupon.setIs_get("yes");
            }
            returnCoupon.setCouPon(couPon);
            returnCoupons.add(returnCoupon);
        }

        return returnCoupons;
    }

    @GetMapping("/findAllSys")
    public List<ReturnCoupon>  findAllSys(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        List<ReturnCoupon> returnCoupons = new ArrayList<>();
        List<CouPon> bySys  = couPonService.findBySys();
        for(int i = 0;i<bySys.size();i++){
            ReturnCoupon returnCoupon = new ReturnCoupon();
            CouPon couPon = bySys.get(i);
            int id = couPon.getId();
            CouponSys byIdAndUser = couponSysService.findByIdAndUser(id+"", user_id);
            if(byIdAndUser == null){
                returnCoupon.setIs_get("no");
            }else {
                returnCoupon.setIs_get("yes");
            }
            returnCoupon.setCouPon(couPon);
            returnCoupons.add(returnCoupon);
        }

        return returnCoupons;
    }

    @GetMapping("/save")
    public Result save(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");
        String coupon_id = request.getParameter("coupon_id");
        //领取优惠券
        CouponSys byIdAndUser = couponSysService.findByIdAndUser(coupon_id, user_id);
        if (byIdAndUser != null) {
            return Result.ok("isGetCoupon");
        }
        CouPon byId = couPonService.findById(coupon_id);
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d2 = df1.parse(byId.getLast_time());
        if (d2.getTime() > System.currentTimeMillis()) {
            //如果领券开始时间小于现在时间说明还没到领券时间
            return Result.ok("isNotStart");
        }
        if (byId.getIs_num().equals("1")) {
            if (Integer.parseInt(byId.getNum()) <= 0) {
                return Result.ok("notEnough");
            }
        }
        String name = byId.getName();
        int id = byId.getId();
        String about_mess = byId.getAbout_mess();
        String category = byId.getCategory();
        String category_tip = byId.getCategory_tip();
        String enough_price = byId.getEnough_price();
        String price = byId.getPrice();
        String goods_id = byId.getGoods_id();
        String coupon_status = byId.getCoupon_status();
        String shop_id = byId.getShop_id();
        String long_time = byId.getLong_time();
        String last_time = byId.getLast_time();
        CouponSys couponSys = new CouponSys();
        couponSys.setAbout_mess(about_mess);
        couponSys.setCategory(category);
        couponSys.setCategory_tip(category_tip);
        couponSys.setCoupon_id(id + "");
        couponSys.setGoods_id(goods_id);
        couponSys.setName(name);
        couponSys.setUser_id(user_id);
        couponSys.setEnough_price(enough_price);
        couponSys.setPrice(price);
        couponSys.setWhere_from(coupon_status);
        couponSys.setShop_id(shop_id);
        couponSys.setLong_time(long_time);

        couponSys.setStart_time(last_time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        couponSys.setCreate_time(df.format(new Date()));
        couponSys.setStatus("1");

        //逻辑判断
        if (byId.getUser_status().equals("1")) {
            //所有人可用
            if (byId.getIs_num().equals("1")) {
                int i = Integer.parseInt(byId.getNum());
                int num = i - 1;
                couPonService.updateNum(num+"",coupon_id);
            }
            couponSysService.saveCoupon(couponSys);
            return Result.ok("ok");
        } else if (byId.getUser_status().equals("2")) {
            //会员才能领取
            UserInfoSystem byId1 = userInfoService.findById(user_id);
            String is_vip = byId1.getIs_vip();
            if (is_vip.equals("1")) {
                if (byId.getIs_num().equals("1")) {
                    int i = Integer.parseInt(byId.getNum());
                    int num = i - 1;
                    couPonService.updateNum(num+"",coupon_id);
                }
                couponSysService.saveCoupon(couponSys);
                return Result.ok("ok");
            }
            return Result.ok("notVip");
        } else if(byId.getUser_status().equals("3")){
            UserInfoSystem byId1 = userInfoService.findById(user_id);
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d3 = df2.parse(byId1.getCreate_time());
            long time = d3.getTime();
            long l = System.currentTimeMillis();
            if(time+604800000>l){
                if (byId.getIs_num().equals("1")) {
                    int i = Integer.parseInt(byId.getNum());
                    int num = i - 1;
                    couPonService.updateNum(num+"",coupon_id);
                }
                couponSysService.saveCoupon(couponSys);
                return Result.ok("ok");
            }
            return Result.ok("isNotNewPeople");
        }else {
            return Result.ok("ok");
        }
    }
}

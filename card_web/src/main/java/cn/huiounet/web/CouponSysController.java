package cn.huiounet.web;

import cn.huiounet.pojo.coupon.CouponSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.CouponSysService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponSysController {
    private static final Logger logger = Logger.getLogger(CouponSysController.class);
    @Autowired
    private CouponSysService couponSysService;

    @GetMapping("/findAll")
    public List<CouponSys> findAll(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

        List<CouponSys> byUser = couponSysService.findByUser(user_id);

        return byUser;
    }

    @GetMapping("/updateCoupon")
    public Result updateCoupon(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String id = request.getParameter("id");
        String status = request.getParameter("status");
        if(status.equals("1")){
            CouponSys byId = couponSysService.findById(id);
            String long_time = byId.getLong_time();
            int days = Integer.parseInt(long_time);
            long how_long_time = days * 86400000L;
            Date date = new Date();
            long now_time = date.getTime();
            long last_time = how_long_time + now_time;
            couponSysService.updateTime(now_time+"",last_time+"",id);
        }

        logger.info("优惠券"+id+"被使用"+"状态为:"+status);

        couponSysService.updateById(status,id);

        return Result.ok("ok");
    }
}

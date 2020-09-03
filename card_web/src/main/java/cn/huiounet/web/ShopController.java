package cn.huiounet.web;

import cn.huiounet.pojo.shop.ShopSc;
import cn.huiounet.pojo.shop.ShopSys;
import cn.huiounet.pojo.shop.ShopVip;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.ShopScService;
import cn.huiounet.service.ShopSysService;
import cn.huiounet.service.ShopVipService;
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
@RequestMapping("/shop")
public class ShopController  {
    @Autowired
    private ShopSysService shopSysService;

    @Autowired
    private ShopVipService shopVipService;

    @Autowired
    private ShopScService shopScService;

    @GetMapping("/findByOpenId")
    public ShopSys findByOpenId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String open_id = request.getParameter("open_id");

        ShopSys byOpenId = shopSysService.findByOpenId(open_id);


        return byOpenId;
    }

    @GetMapping("/findById")
    public ShopSys findById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        ShopSys byOpenId = shopSysService.findByOpenId(id);


        return byOpenId;
    }

    @GetMapping("/findShopVip")
    public List<ShopVip> findShopVip(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String shop_id = request.getParameter("shop_id");

        List<ShopVip> byShopId = shopVipService.findByShopId(shop_id);


        return byShopId;
    }

    @GetMapping("/sc")
    public Result ShopSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        if(shopScService.findByUserId(user_id,shop_id) == null){
            ShopSc shopSc = new ShopSc();

            shopSc.setUser_id(user_id);
            shopSc.setShop_id(shop_id);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            shopSc.setCreate_time(df.format(new Date()));
            shopScService.saveSc(shopSc);

            ShopSys byOpenId = shopSysService.findByOpenId(shop_id);

            String fans = byOpenId.getFans();

            int i = Integer.parseInt(fans);

            int fans_ = i + 1;

            shopSysService.updateFans(fans_+"",shop_id);

            return Result.ok("ok");
        }else {
            return Result.ok("error:已经收藏了");
        }
    }

    @GetMapping("/deleteSc")
    public void deleteSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        shopScService.deleteSc(user_id,shop_id);

        ShopSys byOpenId = shopSysService.findByOpenId(shop_id);

        String fans = byOpenId.getFans();

        int i = Integer.parseInt(fans);

        int fans_ = i - 1;

        shopSysService.updateFans(fans_+"",shop_id);
    }

    @GetMapping("/findSc")
    public Result findSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        ShopSc byUserId = shopScService.findByUserId(user_id, shop_id);

        if(byUserId == null){
            return Result.ok("no");
        }else {
            return Result.ok("yes");
        }
    }
}

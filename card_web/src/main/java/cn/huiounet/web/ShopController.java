package cn.huiounet.web;

import cn.huiounet.pojo.shop.ShopSys;
import cn.huiounet.service.ShopSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shop")
public class ShopController  {
    @Autowired
    private ShopSysService shopSysService;

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
}

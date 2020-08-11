package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.pingjia.PingJiaSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.PingJiaSysService;
import cn.huiounet.service.UserInfoService;
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
@RequestMapping("/pingjia")
public class PingJiaController {
    @Autowired
    private PingJiaSysService pingJiaSysService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GoodsSysService goodsSysService;

    @GetMapping("save")
    public Result savePingJia(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String mess = request.getParameter("pingjiaMess");
        String star = request.getParameter("star");
        String img_one = request.getParameter("img_one");
        String img_two = request.getParameter("img_two");
        String user_id = request.getParameter("user_id");
        String order_num = request.getParameter("order_num");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String goods_id = request.getParameter("goods_id");

        PingJiaSys pingJiaSys = new PingJiaSys();
        pingJiaSys.setColor(color);
        pingJiaSys.setGoods_id(goods_id);
        pingJiaSys.setSzie(size);
        pingJiaSys.setImg_one(img_one);
        pingJiaSys.setImg_two(img_two);
        pingJiaSys.setOrder_num(order_num);
        pingJiaSys.setPingjia(mess);
        pingJiaSys.setStar(star);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pingJiaSys.setTime(df.format(new Date()));
        pingJiaSys.setUser_id(user_id);
        UserInfoSystem byId = userInfoService.findById(user_id);
        pingJiaSys.setUser_img(byId.getHead_img());
        pingJiaSys.setUser_name(byId.getNick_name());

        pingJiaSysService.savePingJia(pingJiaSys);

        return Result.ok("ok");
    }

    @GetMapping("/findByOrderNum")
    public List<PingJiaSys> findByOrderNum(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        List<PingJiaSys> byOrderNum = pingJiaSysService.findByOrderNum(order_num);

        return byOrderNum;
    }

    @GetMapping("/findByUserId")
    public List<PingJiaSys> findByUserId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String start = request.getParameter("start");
        List<PingJiaSys> byUser_id = pingJiaSysService.findByUser_id(user_id,Integer.parseInt(start),5);

        return byUser_id;
    }


    @GetMapping("/deleteById")
    public void deleteById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        pingJiaSysService.deleteById(id);
    }

    @GetMapping("/findByGoodsId")
    public List<PingJiaSys> findByGoodsId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.findByGoodsId(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }
}

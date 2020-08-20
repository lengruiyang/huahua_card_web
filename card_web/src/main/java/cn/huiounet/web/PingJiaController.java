package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.pojo.pingjia.PingJiaNum;
import cn.huiounet.pojo.pingjia.PingJiaSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
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
    private OrderSysService orderSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

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
        String returnGoods = request.getParameter("returnGoods");

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
        returnGoodsService.updateByOrderNum("1",returnGoods);
        List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
        int count = 0;
        for(int i = 0;i<byOrderNUm.size();i++){
            ReturnGoods returnGoods1 = byOrderNUm.get(i);
            String is_pj = returnGoods1.getIs_pj();
            if(is_pj != null){
                count = count + 1;
            }
        }
        int size1 = byOrderNUm.size();
        if(size1 == count){
            orderSysService.updatePj("1",order_num);
        }

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

    @GetMapping("/findByGoodsPj")
    public List<PingJiaSys> findByGoodsPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.GoodsPj(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByPoorPj")
    public List<PingJiaSys> findByPoorPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.PoorPj(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByMPj")
    public List<PingJiaSys> findByMPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.MPj(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByImgPj")
    public List<PingJiaSys> findByImgPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.findByImg(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByPjNum")
    public PingJiaNum findByPjNum(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String goods_id = request.getParameter("goods_id");

        int goodPj = pingJiaSysService.findByNum("1", goods_id);
        int mPj = pingJiaSysService.findByNum("2", goods_id);
        int poorPj = pingJiaSysService.findByNum("3", goods_id);
        int imgPj = pingJiaSysService.findByNum("4", goods_id);
        int allPj = pingJiaSysService.findByNum("5", goods_id);

        PingJiaNum pingJiaNum = new PingJiaNum(goodPj,poorPj,mPj,imgPj,allPj);

        return pingJiaNum;
    }

    @GetMapping("/findByUserGoodsId")
    public List<PingJiaSys> findByUserGoodsId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        String user_id = request.getParameter("user_id");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.findByUserGoodsId(goods_id,user_id);

        return byGoodsIds;
    }
}

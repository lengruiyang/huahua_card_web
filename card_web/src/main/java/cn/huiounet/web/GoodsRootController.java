package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.root.GoodsRoot;
import cn.huiounet.pojo.root.ReturnGoodsRoot;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.GoodsRootService;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("root")
public class GoodsRootController  {
    @Autowired
    private GoodsRootService goodsRootService;
    @Autowired
    private GoodsSysService goodsSysService;

    @GetMapping("save")
    public void redisSave(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");
        GoodsSys id = goodsSysService.findId(goods_id);
        String goods_cen_img = id.getGoods_cen_img();
        String goods_name = id.getGoods_name();
        String low_price = id.getLow_price();
        String hight_price = id.getHight_price();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        long dateToSecond = 0;
        try {
             dateToSecond = df.parse(format).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GoodsRoot goodsRoot = new GoodsRoot();
        goodsRoot.setCreate_time(dateToSecond+"");
        goodsRoot.setGoods_id(goods_id);
        goodsRoot.setGoods_img(goods_cen_img);
        goodsRoot.setGoods_name(goods_name);
        goodsRoot.setPrice(low_price+"."+hight_price);
        goodsRoot.setUser_id(user_id);
        goodsRootService.save(goodsRoot);
    }

    @GetMapping("find")
    public  List<ReturnGoodsRoot> findByuserId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String start = request.getParameter("start");
        List<GoodsRoot> byUserId = goodsRootService.findByUserId(user_id, Integer.parseInt(start), 5);
        List<ReturnGoodsRoot> returnGoodsRoots = new ArrayList<>();
        for(int i = 0;i<byUserId.size();i++){
            GoodsRoot goodsRoot = byUserId.get(i);
            String create_time = goodsRoot.getCreate_time();
            long l = Long.parseLong(create_time);
            Date date = new Date();
            date.setTime(l);
            String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
            ReturnGoodsRoot returnGoodsRoot = new ReturnGoodsRoot(goodsRoot,format);
            returnGoodsRoots.add(returnGoodsRoot);
        }

        return returnGoodsRoots;
    }
}

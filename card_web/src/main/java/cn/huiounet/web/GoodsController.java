package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSc;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.GoodsColorService;
import cn.huiounet.service.GoodsScService;
import cn.huiounet.service.GoodsSizeService;
import cn.huiounet.service.GoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private GoodsScService goodsScService;

    @Autowired
    private GoodsSizeService goodsSizeService;

    @Autowired
    private GoodsColorService goodsColorService;

    @GetMapping("/findAll")
    public List<GoodsSys> findAll(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String start = request.getParameter("start");
        String pageSize = request.getParameter("pageSize");
        List<GoodsSys> goodsList = goodsSysService.findAll(Integer.parseInt(start), Integer.parseInt(pageSize));

        return goodsList;
    }


    @GetMapping("/findNewGoods")
    public List<GoodsSys> findNewGoods(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        List<GoodsSys> goodsList = goodsSysService.findNewGoods();

        return goodsList;
    }

    @GetMapping("/searchGoods")
    public List<GoodsSys> searchGoods(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String goods_name = request.getParameter("goods_name");

        List<GoodsSys> goodsList = goodsSysService.SearchGoods(goods_name);

        return goodsList;
    }

    //推荐算法
    //

    @GetMapping("/findById")
    public GoodsSys findById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        GoodsSys goods = goodsSysService.findId(id);

        return goods;
    }

    @GetMapping("/saveSc")
    public Result saveSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");
        GoodsSc goodsSc = new GoodsSc();
        goodsSc.setGoods_id(goods_id);
        GoodsSys id = goodsSysService.findId(goods_id);
        String like_many = id.getLike_many();
        int i = Integer.parseInt(like_many) + 1;
        goodsSysService.updateLike(i+"",goods_id);
        goodsSc.setUser_id(user_id);
        goodsScService.saveGoodsSc(goodsSc);
        //成功
        return Result.ok("ok");
    }

    @GetMapping("/findSc")
    public GoodsSc findSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");

        GoodsSc sc = goodsScService.findSc(goods_id, user_id);
        //成功
        return sc;
    }

    @GetMapping("/deleteSc")
    public Result deleteSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");
        GoodsSys id = goodsSysService.findId(goods_id);
        String like_many = id.getLike_many();
        int i = Integer.parseInt(like_many);
        int i1 = i - 1;

        goodsSysService.updateLike(i1+"",goods_id);

        goodsScService.deleteById(goods_id,user_id);
        //成功
        return Result.ok("ok");
    }


    @GetMapping("/findColor")
    public List<GoodsColor> findColor(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        List<GoodsColor> goodsColor = goodsColorService.findGoodsColor(goods_id);
        //成功
        return goodsColor;
    }

    @GetMapping("/findSell")
    public List<GoodsSys> findSell(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String start = request.getParameter("start");

        List<GoodsSys> bySell = goodsSysService.findBySell(Integer.parseInt(start), 5);
        //成功
        return bySell;
    }

    @GetMapping("/findLike")
    public List<GoodsSys> findLike(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String start = request.getParameter("start");

        List<GoodsSys> byLike = goodsSysService.findByLike(Integer.parseInt(start), 5);
        //成功
        return byLike;
    }

    @GetMapping("/findSize")
    public List<GoodsSize> findSize(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String color_id = request.getParameter("color_id");
        List<GoodsSize> goodsSize = goodsSizeService.findGoodsSize(color_id);
        //成功
        return goodsSize;
    }

}

package cn.huiounet.web;

import cn.huiounet.pojo.diandan.*;
import cn.huiounet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/diandan")
public class OrderDianDanController {
    @Autowired
    private OrderDianDanShopService orderDianDanShopService;
    @Autowired
    private OrderDianDanFenLeiService orderDianDanFenLeiService;
    @Autowired
    private OrderDianDanQrCodeService orderDianDanQrCodeService;
    @Autowired
    private DianDanGoodsService dianDanGoodsService;
    @Autowired
    private DianDanGoodsFenLeiSonService dianDanGoodsFenLeiSonService;
    @Autowired
    private DianDanGoodsFenLeiFartherService dianDanGoodsFenLeiFartherService;

    @GetMapping("/findGoodsFenLei")
    public List<ReturnFenLei> findGoodsFenLei(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String goods_id = request.getParameter("goods_id");

        List<DianDanGoodsFenLeiFarther> byGoodsId = dianDanGoodsFenLeiFartherService.findByGoodsId(goods_id);
        List<ReturnFenLei> returnFenLeis = new ArrayList<>();
        for(int i = 0;i<byGoodsId.size();i++){
            DianDanGoodsFenLeiFarther dianDanGoodsFenLeiFarther = byGoodsId.get(i);
            ReturnFenLei returnFenLei = new ReturnFenLei();
            returnFenLei.setTitle(dianDanGoodsFenLeiFarther.getName());
            returnFenLei.setSelected(1);
            List<DianDanGoodsFenLeiSon> byFatherId = dianDanGoodsFenLeiSonService.findByFatherId(dianDanGoodsFenLeiFarther.getId()+"");
            returnFenLei.setList(byFatherId);
            returnFenLeis.add(returnFenLei);
        }

        return returnFenLeis;
    }

    @GetMapping("/findGoods")
    public DianDanGoods findGoods(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        DianDanGoods byId = dianDanGoodsService.findById(Integer.parseInt(id));

        return byId;
    }

    @GetMapping("/findShop")
    public OrderDianDanShop findByShopId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        OrderDianDanShop byId = orderDianDanShopService.findById(Integer.parseInt(id));

        return byId;
    }

    @GetMapping("/findFenLei")
    public List<ReturnDianDanList> findFenLei(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String shop_id = request.getParameter("shop_id");

        List<OrderDianDanFenLei> byShopId = orderDianDanFenLeiService.findByShopId(shop_id);

        List<ReturnDianDanList> returnDianDanLists = new ArrayList<>();
        for(int i = 0;i<byShopId.size();i++){
            OrderDianDanFenLei orderDianDanFenLei = byShopId.get(i);
            int id = orderDianDanFenLei.getId();
            List<DianDanGoods> byFenLeiId = dianDanGoodsService.findByFenLeiId(id+"");
            ReturnDianDanList returnDianDanList = new ReturnDianDanList(orderDianDanFenLei,byFenLeiId);
            returnDianDanLists.add(returnDianDanList);
        }
        return returnDianDanLists;
    }

}

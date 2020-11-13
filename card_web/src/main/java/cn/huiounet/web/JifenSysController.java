package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.address.AddressSys;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.renwujifen.JiFenGoodsFenLeiSys;
import cn.huiounet.pojo.renwujifen.JiFenGoodsSys;
import cn.huiounet.pojo.renwujifen.JiFenOrder;
import cn.huiounet.pojo.renwujifen.JobSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.wxPay.WXPayUtil;
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
@RequestMapping("/jifen")
public class JifenSysController {
    @Autowired
    private JobSysService jobSysService;
    @Autowired
    private JiFenGoodsSysService jiFenGoodsSysService;
    @Autowired
    private JiFenGoodsFenLeiSysService jiFenGoodsFenLeiSysService;
    @Autowired
    private JiFenOrderService jiFenOrderService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AddressSysService addressSysService;

    @GetMapping("/findAll")
    public List<JobSys> findAll(){
        List<JobSys> all = jobSysService.findAll();

        return all;
    }

    @GetMapping("findFenLei")
    public List<JiFenGoodsFenLeiSys> findFenLei(){
        List<JiFenGoodsFenLeiSys> allStatus = jiFenGoodsFenLeiSysService.findAllStatus();

        return allStatus;
    }

    @GetMapping("/findByFenLeiId")
    public List<JiFenGoodsSys> findByFenLeiId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String fenlei_id = request.getParameter("fenlei_id");
        String start = request.getParameter("start");

        List<JiFenGoodsSys> byFenLei = jiFenGoodsSysService.findByFenLei(fenlei_id,Integer.parseInt(start),5);

        return byFenLei;
    }

    @GetMapping("/findByJiFenGoodsId")
    public JiFenGoodsSys findByJiFenGoodsId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");
        JiFenGoodsSys byId = jiFenGoodsSysService.findById(id);
        return byId;
    }

    @GetMapping("/createOrder")
    public String createOrder(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        String user_id = request.getParameter("user_id");
        String nonceStr = WXPayUtil.generateUUID(); //订单号
        JiFenGoodsSys byId = jiFenGoodsSysService.findById(goods_id);
        String goods_cen_img = byId.getGoods_cen_img();
        String goods_name = byId.getGoods_name();
        String jifen = byId.getJifen();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JiFenOrder jiFenOrder = new JiFenOrder();
        jiFenOrder.setCreate_time(df.format(new Date()));
        jiFenOrder.setGoods_name(goods_name);
        jiFenOrder.setImg(goods_cen_img);
        jiFenOrder.setJifen_num(jifen);
        jiFenOrder.setUser_id(user_id);
        jiFenOrder.setOrder_num(nonceStr);
        jiFenOrder.setIs_change("is_not");
        jiFenOrder.setNum("1");
        AddressSys byStatus = addressSysService.findByStatus(user_id, "1");
        int id = byStatus.getId();
        jiFenOrder.setAddress_id(id+"");
        if(byId.getIs_need_price().equals("1")){
            jiFenOrder.setIs_need_money(byId.getPrice_num());
        }
        jiFenOrderService.saveOrder(jiFenOrder);
        return nonceStr;
    }

    @GetMapping("/findOrder")
    public JiFenOrder findOrder(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        JiFenOrder byOrderNum = jiFenOrderService.findByOrderNum(order_num);

        return byOrderNum;
    }

    @GetMapping("/updateAddress")
    public void updateAddress(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        String address_id = request.getParameter("address_id");
        jiFenOrderService.updateAddressByOrderNum(address_id,order_num);
    }

    @GetMapping("/payOrder")
    public Result payOrder(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        JiFenOrder byOrderNum = jiFenOrderService.findByOrderNum(order_num);

        String jifen_num = byOrderNum.getJifen_num();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserInfoSystem byId = userInfoService.findById(byOrderNum.getUser_id());

        String jifen = byId.getJifen();

        int i = Integer.parseInt(jifen) - Integer.parseInt(jifen_num);

        if(i < 0){
            return Result.ok("fail");
        }else {
            jiFenOrderService.updateByOrderNum("is_pay",df.format(new Date()),order_num);
            userInfoService.updateJiFen(i+"",byId.getId()+"");
            return Result.ok("ok");
        }
    }
}

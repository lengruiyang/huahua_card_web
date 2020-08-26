package cn.huiounet.web;

import cn.huiounet.pojo.fapiao.OrderFPsys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.OrderFPsysService;
import cn.huiounet.service.OrderSysService;
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
@RequestMapping("/fp")
public class OrderFPsysController {
    @Autowired
    private OrderFPsysService orderFPsysService;

    @Autowired
    private OrderSysService orderSysService;

    @GetMapping("/save")
    public Result saveFP(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        String company_name = request.getParameter("company_name");
        String company_code = request.getParameter("company_code");
        String company_address = request.getParameter("company_address");
        String company_phone = request.getParameter("company_phone");
        String company_bank = request.getParameter("company_bank");
        String bampany_card = request.getParameter("bampany_card");
        String user_id = request.getParameter("user_id");
        String status = request.getParameter("status");


        OrderFPsys orderFPsys = new OrderFPsys();
        orderFPsys.setBampany_card(bampany_card);
        orderFPsys.setCompany_address(company_address);
        orderFPsys.setCompany_phone(company_phone);
        orderFPsys.setPhone(phone);
        orderFPsys.setOrder_num(order_num);
        orderFPsys.setEmail(email);
        orderFPsys.setUser_id(user_id);
        orderFPsys.setUser_name(user_name);
        orderFPsys.setCompany_name(company_name);
        orderFPsys.setCompany_code(company_code);
        orderFPsys.setCompany_bank(company_bank);
        orderFPsys.setStatus(status);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderFPsys.setCreate_time(df.format(new Date()));

        orderSysService.updateFP("1",order_num);

        orderFPsysService.saveFP(orderFPsys);

        return Result.ok("ok");
    }


    @GetMapping("/findByNum")
    public OrderFPsys findByNum(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");

        OrderFPsys byOrderNum = orderFPsysService.findByOrderNum(order_num);

        return byOrderNum;
    }

    @GetMapping("/findByUser")
    public List<OrderFPsys> findByUser(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");

        List<OrderFPsys> byUserId = orderFPsysService.findByUserId(user_id);

        return byUserId;
    }
}

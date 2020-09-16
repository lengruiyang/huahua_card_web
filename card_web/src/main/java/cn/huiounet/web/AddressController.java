package cn.huiounet.web;

import cn.huiounet.pojo.address.AddressSys;
import cn.huiounet.pojo.address.AddressTip;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.AddressSysService;
import cn.huiounet.service.AddressTipService;
import org.apache.log4j.Logger;
import org.elasticsearch.cluster.metadata.AliasAction;
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
@RequestMapping("/address")
public class AddressController {
    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private AddressTipService addressTipService;

    @Autowired
    private AddressSysService addressSysService;

    @GetMapping("/tips")
    public List<AddressTip> findAllTips(){

        List<AddressTip> addressTips = addressTipService.findAll();

        return addressTips;
    }

    @GetMapping("/getAddress")
    public AddressSys getAddress(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");

        AddressSys byStatus = addressSysService.findByStatus(user_id, "1");

        if (byStatus == null){
            return null;
        }
        return byStatus;
    }

    @GetMapping("/save")
    public Result saveAddress(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        AddressSys addressSys = new AddressSys();

        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String user_phone = request.getParameter("user_phone");
        String user_address = request.getParameter("user_address");
        String tip = request.getParameter("tip");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        addressSys.setUser_id(user_id);
        if(addressSysService.findByStatus(user_id,"1") == null){
            addressSys.setStatus("1");
        }else {
            addressSys.setStatus("0");
        }
        addressSys.setCreate_time(df.format(new Date()));
        addressSys.setUser_name(user_name);
        addressSys.setUser_phone(user_phone);
        addressSys.setUser_address(user_address);
        addressSys.setTip(tip);


        addressSysService.saveAddress(addressSys);

        logger.info("用户："+user_id+"新建地址");

        return Result.ok("ok");
    }

    @GetMapping("/find")
    public List<AddressSys> findByUserId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

        List<AddressSys> byUser_id = addressSysService.findByUser_id(user_id);

        return byUser_id;
    }

    @GetMapping("/findById")
    public AddressSys findById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String id = request.getParameter("id");

        AddressSys byId = addressSysService.findById(id);

        return byId;
    }

    @GetMapping("/delete")
    public Result deleteById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String id = request.getParameter("id");
        String user_id = request.getParameter("user_id");
        AddressSys byId = addressSysService.findById(id);
        addressSysService.deleteById(id);
        if(addressSysService.findByUser_id(user_id).size() != 0){
            if(byId.getStatus().equals("1")){
                //如果是默认地址
                //改变默认地址
                List<AddressSys> byUser_id = addressSysService.findByUser_id(user_id);
                AddressSys addressSys = byUser_id.get(0);
                addressSysService.updateStatus("1",addressSys.getId()+"");
            }
        }
        logger.info("用户"+user_id+"删除地址Id:"+id);
        return Result.ok("ok");
    }
}

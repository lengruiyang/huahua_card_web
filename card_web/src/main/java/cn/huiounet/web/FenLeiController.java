package cn.huiounet.web;

import cn.huiounet.pojo.fenlei.FenLei_Farther_sys;
import cn.huiounet.pojo.fenlei.FenLei_Son_sys;
import cn.huiounet.service.FenLei_Farther_sysService;
import cn.huiounet.service.FenLei_Son_sysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/fenlei")
public class FenLeiController  {
    @Autowired
    private FenLei_Son_sysService fenLei_son_sysService;

    @Autowired
    private FenLei_Farther_sysService fenLei_farther_sysService;

    @GetMapping("findAll_farther")
    public List<FenLei_Farther_sys> findAll_farther(){

        List<FenLei_Farther_sys> fenLei_farther_syss = fenLei_farther_sysService.findAll();

        return fenLei_farther_syss;
    }

    @GetMapping("findAll_son")
    public List<FenLei_Son_sys> findAll_son(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String farther_id = request.getParameter("farther_id");

        List<FenLei_Son_sys> byFartherId = fenLei_son_sysService.findByFartherId(farther_id);

        return byFartherId;
    }
}

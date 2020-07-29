package cn.huiounet.web;

import cn.huiounet.pojo.search.SearchSys;
import cn.huiounet.service.SearchSysService;
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
@RequestMapping("/search")
public class SearchSysController {
    @Autowired
    private SearchSysService searchSysService;

    @GetMapping("/save")
    public void saveSearch(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");
        String mess = request.getParameter("mess");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SearchSys searchSys = new SearchSys();
        searchSys.setSearch_mess(mess);
        searchSys.setSearch_time(df.format(new Date()));
        searchSys.setUser_id(user_id);

        searchSysService.saveSerarch(searchSys);
    }


    @GetMapping("/findSearch")
    public List<SearchSys> findSearch(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

        List<SearchSys> searchSysList = searchSysService.findByUser(user_id);

        return searchSysList;
    }
}

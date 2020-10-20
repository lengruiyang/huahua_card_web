package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.search.SearchCount;
import cn.huiounet.pojo.search.SearchSys;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.SearchCountService;
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

    @Autowired
    private SearchCountService searchCountService;

    @Autowired
    private GoodsSysService goodsSysService;

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
        SearchCount bySearchMess = searchCountService.findBySearchMess(mess);

        if(bySearchMess == null){
            SearchCount searchCount = new SearchCount();
            searchCount.setCount("1");
            searchCount.setSearch_mess(mess);
            searchCountService.saveSearchMess(searchCount);
        }else {
            String count = bySearchMess.getCount();
            int i = Integer.parseInt(count);
            i += 1;
            searchCountService.updateBySerach(i,mess);
        }

        if(searchSysService.findByUserAndMess(user_id,mess) == null){
            searchSysService.saveSerarch(searchSys);
        }
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

    @GetMapping("/findOrderBy")
    public List<SearchCount> findOrderBy(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        List<SearchCount> bySearchOrderBy = searchCountService.findBySearchOrderBy(0, 5);

        return bySearchOrderBy;
    }


    @GetMapping("search")
    public List<GoodsSys> searchGoods(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String goods_name = request.getParameter("goods_name");

        String start = request.getParameter("start");

        List<GoodsSys> goodsSys = goodsSysService.searchGoods(goods_name, Integer.parseInt(start), 5);

        return goodsSys;
    }



    @GetMapping("/delete")
    public void delete(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

       searchSysService.deleteByUser(user_id);

    }


    @GetMapping("/findLike")
    public List<SearchCount> findLike(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String mess = request.getParameter("mess");

        List<SearchCount> searchCounts = searchCountService.searchLike(mess, 0, 8);

        return searchCounts;

    }


}

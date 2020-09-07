package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import cn.huiounet.pojo.miaosha.MiaoShaSys;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.MiaoShaGoodsSysService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/miaosha")
public class MiaoShaController {
    private static final Logger logger = Logger.getLogger(MiaoShaController.class);

    @Autowired
    private MiaoShaGoodsSysService miaoShaGoodsSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    /**
     * 每晚12：00触发秒杀清零机制
     */
    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 0 * * ?")
    public void setMiaoSha(){
        List<MiaoShaGoodsSys> all = miaoShaGoodsSysService.findAll();
        for(int i = 0;i<all.size();i++){
            MiaoShaGoodsSys miaoShaGoodsSys = all.get(i);
            String start_time = miaoShaGoodsSys.getStart_time();
            long l = Long.parseLong(start_time);
            long return_time = l + 86400000;
            miaoShaGoodsSysService.updateStartTime(return_time+"",miaoShaGoodsSys.getId()+"");
        }
        logger.info("凌晨12点任务执行");
    }

    @GetMapping("/miaoshaList")
    public List<MiaoShaSys> miaoShaList(){
        List<MiaoShaGoodsSys> all = miaoShaGoodsSysService.findAll();
        List<MiaoShaSys> miaoShaSysList = new ArrayList<>();
        for(int i = 0;i<all.size();i++){
            MiaoShaSys miaoShaSys = new MiaoShaSys();
            MiaoShaGoodsSys miaoShaGoodsSys = all.get(i);
            String id = miaoShaGoodsSys.getId();
            miaoShaSys.setId(id);
            String name = miaoShaGoodsSys.getName();
            String start_time = miaoShaGoodsSys.getStart_time();
            String long_time = miaoShaGoodsSys.getLong_time();
            Long end_time = Long.parseLong(start_time)+Long.parseLong(long_time);
            long l = System.currentTimeMillis(); //现在时间
            long last_time = end_time - l;
            if(l>Long.parseLong(start_time)){
                if(l<end_time){
                    //已经开始秒杀正在秒杀
                    miaoShaSys.setStatus("1");
                    miaoShaSys.setTime(last_time/1000);
                }else {
                    //已经结束了秒杀
                    miaoShaSys.setStatus("2");
                    miaoShaSys.setTime(last_time/1000);
                }
            }else {
                //还没开始
                miaoShaSys.setStatus("0");
                miaoShaSys.setTime(last_time/1000);
                long now_time = System.currentTimeMillis();
                long st =  Long.parseLong(start_time) - now_time;
                miaoShaSys.setStart_time(st/1000);
            }
            miaoShaSys.setName(name);
            miaoShaSysList.add(miaoShaSys);
        }
        return miaoShaSysList;
    }

    @GetMapping("/goods")
    public List<GoodsSys> findMiaoshaGoodsList(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String start = request.getParameter("start");
        String miaosha_id = request.getParameter("miaosha_id");

        List<GoodsSys> miaoShaGoodsList = goodsSysService.findMiaoShaGoodsList(miaosha_id, Integer.parseInt(start), 5);

        return miaoShaGoodsList;
    }
}
package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import cn.huiounet.pojo.miaosha.MiaoShaSys;
import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.MiaoShaGoodsSysService;
import cn.huiounet.service.YuYueMiaoShaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/miaosha")
public class MiaoShaController {
    private static final Logger logger = Logger.getLogger(MiaoShaController.class);

    @Autowired
    private MiaoShaGoodsSysService miaoShaGoodsSysService;

    @Autowired
    private YuYueMiaoShaService yuYueMiaoShaService;

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

    @GetMapping("/miaosha_sys")
    public void miaosha_sys()throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(format+" 00:00");
        long longDate = date.getTime();
        int id = 15;
        for (int i = 0; i < 11; i++) {
            MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById(id + "");
            miaoShaGoodsSysService.updateStartTime(longDate + "", byId.getId() + "");
            longDate += 7200000;
            id+=1;
        }
        logger.info("格式化秒杀执行");
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

    @GetMapping("/findById")
    public Long findById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById(id);
        String start_time = byId.getStart_time();
        String long_time = byId.getLong_time();
        long st = Long.parseLong(start_time);
        long lo = Long.parseLong(long_time);
        long end_time = st + lo;
        long l = System.currentTimeMillis();
        long l1 = end_time - l;

        return  (l1)/1000;
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

    @GetMapping("/yuyue")
    public Result yuYueMiaoSha(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");
        String goods_id = request.getParameter("goods_id");
        GoodsSys id = goodsSysService.findId(goods_id);
        String miaosha_id = id.getMiaosha_id();

        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById(miaosha_id);
        //判断相同的预约
        if(yuYueMiaoShaService.findByUserIdAndStartTime(user_id,byId.getStart_time()) != null){
            return Result.ok("fail");
        }

        YuYueMiaoSha yuYueMiaoSha = new YuYueMiaoSha();


        String start_time = byId.getStart_time();

        yuYueMiaoSha.setGoods_id(goods_id);
        yuYueMiaoSha.setStart_time(start_time);
        yuYueMiaoSha.setUser_id(user_id);

        yuYueMiaoShaService.saveYuYue(yuYueMiaoSha);

        logger.info("用户Id:"+user_id+"预约秒杀商品Id"+goods_id);

        return Result.ok("ok");
    }


    @GetMapping("/cancelyuyue")
    public Result cancelyuyue(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

        String goods_id = request.getParameter("goods_id");
        GoodsSys id = goodsSysService.findId(goods_id);
        String miaosha_id = id.getMiaosha_id();
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById(miaosha_id);
        String start_time = byId.getStart_time();

        yuYueMiaoShaService.deleteByUserIdAndStartTime(user_id,start_time);

        logger.info("用户Id:"+user_id+"取消商品预约Id:"+goods_id);
        return Result.ok("ok");
    }


    @GetMapping("/findUserYuYue")
    public Result findUserYuYue(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String user_id = request.getParameter("user_id");

        String goods_id = request.getParameter("goods_id");
        GoodsSys id = goodsSysService.findId(goods_id);
        String miaosha_id = id.getMiaosha_id();

        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById(miaosha_id);


        YuYueMiaoSha byUserIdAndStartTime = yuYueMiaoShaService.findByUserIdAndStartTime(user_id, byId.getStart_time());

        if (byUserIdAndStartTime == null){
            return Result.ok("ok");
        }else {
            return Result.ok("fail");
        }


    }
}

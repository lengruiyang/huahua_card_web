package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.dingyuexiaoxi.Template;
import cn.huiounet.pojo.dingyuexiaoxi.TemplateParam;
import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.pojo.pingjia.PingJIaHF;
import cn.huiounet.pojo.pingjia.PingJiaNum;
import cn.huiounet.pojo.pingjia.PingJiaSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pingjia")
public class PingJiaController {
    private static Logger logger = Logger.getLogger(PingJiaController.class);

    @Autowired
    private PingJiaSysService pingJiaSysService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PingJIaHFService pingJIaHFService;

    @Autowired
    private OrderSysService orderSysService;



    @Autowired
    private ReturnGoodsService returnGoodsService;

    @GetMapping("save")
    public Result savePingJia(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String mess = request.getParameter("pingjiaMess");
        String star = request.getParameter("star");
        String img_one = request.getParameter("img_one");
        String img_two = request.getParameter("img_two");
        String user_id = request.getParameter("user_id");
        String order_num = request.getParameter("order_num");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String goods_id = request.getParameter("goods_id");
        String returnGoods = request.getParameter("returnGoods");

        PingJiaSys pingJiaSys = new PingJiaSys();
        pingJiaSys.setColor(color);
        pingJiaSys.setGoods_id(goods_id);
        pingJiaSys.setSzie(size);
        pingJiaSys.setImg_one(img_one);
        pingJiaSys.setImg_two(img_two);
        pingJiaSys.setOrder_num(order_num);
        pingJiaSys.setPingjia(mess);
        pingJiaSys.setStar(star);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pingJiaSys.setTime(df.format(new Date()));
        pingJiaSys.setUser_id(user_id);
        UserInfoSystem byId = userInfoService.findById(user_id);
        pingJiaSys.setUser_img(byId.getHead_img());
        pingJiaSys.setUser_name(byId.getNick_name());
        pingJiaSysService.savePingJia(pingJiaSys);
        returnGoodsService.updateByOrderNum("1",returnGoods);
        List<ReturnGoods> byOrderNUm = returnGoodsService.findByOrderNUm(order_num);
        int count = 0;
        for(int i = 0;i<byOrderNUm.size();i++){
            ReturnGoods returnGoods1 = byOrderNUm.get(i);
            String is_pj = returnGoods1.getIs_pj();
            if(is_pj != null){
                count = count + 1;
            }
        }
        int size1 = byOrderNUm.size();
        if(size1 == count){
            orderSysService.updatePj("1",order_num);
        }

        return Result.ok("ok");
    }

    @GetMapping("/findByOrderNum")
    public List<PingJiaSys> findByOrderNum(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String order_num = request.getParameter("order_num");
        List<PingJiaSys> byOrderNum = pingJiaSysService.findByOrderNum(order_num);

        return byOrderNum;
    }

    @GetMapping("/findByUserId")
    public List<PingJiaSys> findByUserId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String start = request.getParameter("start");
        List<PingJiaSys> byUser_id = pingJiaSysService.findByUser_id(user_id,Integer.parseInt(start),5);

        return byUser_id;
    }


    @GetMapping("/deleteById")
    public void deleteById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        pingJiaSysService.deleteById(id);
    }



    @GetMapping("/findByGoodsId")
    public List<PingJiaSys> findByGoodsId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.findByGoodsId(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByGoodsPj")
    public List<PingJiaSys> findByGoodsPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.GoodsPj(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByPoorPj")
    public List<PingJiaSys> findByPoorPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.PoorPj(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByMPj")
    public List<PingJiaSys> findByMPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.MPj(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByImgPj")
    public List<PingJiaSys> findByImgPj(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        String start = request.getParameter("start");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.findByImg(goods_id, Integer.parseInt(start), 5);

        return byGoodsIds;
    }

    @GetMapping("/findByPjNum")
    public PingJiaNum findByPjNum(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String goods_id = request.getParameter("goods_id");

        int goodPj = pingJiaSysService.findByNum("1", goods_id);
        int mPj = pingJiaSysService.findByNum("2", goods_id);
        int poorPj = pingJiaSysService.findByNum("3", goods_id);
        int imgPj = pingJiaSysService.findByNum("4", goods_id);
        int allPj = pingJiaSysService.findByNum("5", goods_id);

        PingJiaNum pingJiaNum = new PingJiaNum(goodPj,poorPj,mPj,imgPj,allPj);

        return pingJiaNum;
    }

    @GetMapping("/findByUserGoodsId")
    public List<PingJiaSys> findByUserGoodsId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");
        String user_id = request.getParameter("user_id");

        List<PingJiaSys> byGoodsIds = pingJiaSysService.findByUserGoodsId(goods_id,user_id);

        return byGoodsIds;
    }


    @GetMapping("/findById")
    public PingJiaSys findById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        PingJiaSys byId = pingJiaSysService.findById(id);


        return byId;
    }

    @GetMapping("/saveHf")
    public void saveHf(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String pjId = request.getParameter("pjId");
        String mess = request.getParameter("mess");
        String user_id = request.getParameter("user_id");

        PingJIaHF pingJIaHF = new PingJIaHF();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pingJIaHF.setCreate_time(df.format(new Date()));
        pingJIaHF.setMess(mess);
        pingJIaHF.setStatus("1");
        UserInfoSystem byId = userInfoService.findById(user_id);
        pingJIaHF.setUser_img(byId.getHead_img());
        pingJIaHF.setUser_name(byId.getNick_name());
        pingJIaHF.setPjId(pjId);
        pingJIaHF.setUser_id(user_id);

        pingJIaHFService.savePjHf(pingJIaHF);

        //订阅消息
        //获取access_token
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口


        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.get("token");

        if(token == null){
            token = GetTokenUtil.getToken_wx();

            jedis.set("token", token);

        }
        //订阅消息
        PingJiaSys byId1 = pingJiaSysService.findById(pjId);
        String user_id1 = byId1.getUser_id();
        UserInfoSystem byId2 = userInfoService.findById(user_id1);
        Template template=new Template();
        template.setTemplate_id("o6QvrlRcRgq3bBSOsLXEctxjmprI2Qwnt7z5Wo6U1Vs");
        template.setTouser(byId2.getOpen_id());
        template.setPage("/pages/index/index");
        List<TemplateParam> paras=new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("thing1",byId.getNick_name()));
        paras.add(new TemplateParam("thing2",mess));
        paras.add(new TemplateParam("time3",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));

        template.setTemplateParamList(paras);

        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);


        String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

        logger.info(reqMess);
    }


    @GetMapping("/findHfByPjId")
    public List<PingJIaHF> findHfByPjId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String pjId = request.getParameter("pjId");
        String start = request.getParameter("start");

        List<PingJIaHF> byPjId = pingJIaHFService.findByPjId(pjId,Integer.parseInt(start),5);


        return byPjId;
    }

}

package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.dingyuexiaoxi.Template;
import cn.huiounet.pojo.dingyuexiaoxi.TemplateParam;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.MiaoShaGoodsSysService;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.service.YuYueMiaoShaService;
import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.tuikuan.TuiKuanSys;
import cn.huiounet.utils.wxPay.WXPayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AutoMiaoShaNoticController {
    private static final Logger logger = Logger.getLogger(AutoMiaoShaNoticController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private YuYueMiaoShaService yuYueMiaoShaService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private MiaoShaGoodsSysService miaoShaGoodsSysService;

    public   String noticUtil(String user_id,String time,String name,String price){
        /*订阅消息*/
        //获取access_token
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口


        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.get("token");

        if (token == null) {
            token = GetTokenUtil.getToken_wx();

            jedis.set("token", token);

        }
        //获得openid
        UserInfoSystem byId = userInfoService.findById(user_id);
        String open_id = byId.getOpen_id();
        Template template = new Template();
        template.setTemplate_id("Zr-PDZMrXmmsldf1ilNZuZ1h9J426wMBLVKlfbL2hAs");
        template.setTouser(open_id);
        template.setPage("/pages/index/index");
        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("time1", time));
        paras.add(new TemplateParam("thing2", "您预约的秒杀活动开始了，快来参与吧～"));
        paras.add(new TemplateParam("thing3", name));
        paras.add(new TemplateParam("amount4", price));

        template.setTemplateParamList(paras);

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);


        String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

        return reqMess;
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 2 * * ?")
    public void twoClock(){
        logger.info("2点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("16");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "2:00", goods_name.substring(0, 5) + "...", price);
            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 4 * * ?")
    public void fourClock(){
        logger.info("4点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("17");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "4:00", goods_name.substring(0, 5) + "...", price);
            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 6 * * ?")
    public void sixClock(){
        logger.info("6点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("18");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "6:00", goods_name.substring(0, 5) + "...", price);
            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 8 * * ?")
    public void eightClock(){
        logger.info("8点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("19");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "8:00", goods_name.substring(0, 5) + "...", price);
            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 10 * * ?")
    public void tenClock(){
        logger.info("10点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("20");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "10:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 12 * * ?")
    public void twelveClock(){
        logger.info("12点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("21");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "12:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 14 * * ?")
    public void fourTeenClock(){
        logger.info("14点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("22");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "14:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 16 * * ?")
    public void sixTeenClock(){
        logger.info("16点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("23");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "16:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 18 * * ?")
    public void eightTeenClock(){
        logger.info("18点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("24");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "18:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 20 * * ?")
    public void twentyClock(){
        logger.info("20点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("25");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "20:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 22 * * ?")
    public void TwentyTwoClock(){
        logger.info("20点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("26");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "22:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 0 * * ?")
    public void ZeroClock(){
        logger.info("0点执行");
        MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById("15");
        List<YuYueMiaoSha> byStartTime = yuYueMiaoShaService.findByStartTime(byId.getStart_time());
        if (byStartTime.size() == 0){
            return;
        }
        for(int i =0;i<byStartTime.size();i++){
            YuYueMiaoSha yuYueMiaoSha = byStartTime.get(i);
            String user_id = yuYueMiaoSha.getUser_id();
            String goods_id = yuYueMiaoSha.getGoods_id();
            GoodsSys id = goodsSysService.findId(goods_id);
            String goods_name = id.getGoods_name();
            String low_price = id.getLow_price();
            String hight_price = id.getHight_price();
            String price = low_price+"."+hight_price;
            String s = noticUtil(user_id, "00:00", goods_name.substring(0, 5) + "...", price);

            logger.info("执行结果:用户"+user_id+s);
        }
    }


}

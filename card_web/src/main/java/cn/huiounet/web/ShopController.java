package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.dingyuexiaoxi.Template;
import cn.huiounet.pojo.dingyuexiaoxi.TemplateParam;
import cn.huiounet.pojo.order.OrderAddress;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.shop.AddShop;
import cn.huiounet.pojo.shop.ShopSc;
import cn.huiounet.pojo.shop.ShopSys;
import cn.huiounet.pojo.shop.ShopVip;
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
import java.util.*;

@RestController
@RequestMapping("/shop")
public class ShopController  {
    private static Logger logger = Logger.getLogger(ShopController.class);
    @Autowired
    private ShopSysService shopSysService;

    @Autowired
    private AddShopService addShopService;

    @Autowired
    private ShopVipService shopVipService;

    @Autowired
    private ShopScService shopScService;

    @Autowired
    private UserInfoService userInfoService;
    /**
     * 页面
     * @param page 当前页
     * @param limit 页大小
     * @return
     */
    @GetMapping("/find_xianxia_shop_sys")
    public Map findXianxiaShopSys(int page, int limit){
        int truePage = page - 1;
        int start = truePage * limit;
        List<ShopSys> byZiTi = shopSysService.findByZiTi("1", start, limit);
        Map map = new HashMap();
        map.put("code",0);
        map.put("data",byZiTi);
        map.put("count",shopSysService.findAll());
        return map;
    }

    @GetMapping("/find_Sh_shop_sys")
    public Map find_Sh_shop_sys(int page, int limit){
        int truePage = page - 1;
        int start = truePage * limit;
        List<AddShop> addShops = addShopService.findPage(start, limit);
        Map map = new HashMap();
        map.put("code",0);
        map.put("data",addShops);
        map.put("count",addShopService.AllSize());
        return map;
    }

    @GetMapping("/updateStatus")
    public Result updateAddStatus(String status,String id,String user_id,String beizhu,String refuseMess){

        addShopService.updateStatusById(status,Integer.parseInt(id));

        if(status.equals("1")){
            //通过
            //订阅消息
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
            //得到了模板id;zTNpnRfGK8YgL2i6cctWsaIdPXqUHtdk8F60fxT6RGE
            Template template = new Template();
            template.setTemplate_id("zxzluWczkU7XiiBL_gRY6VCS6Bl7jiFfQHGJ1dgA6tE");
            template.setTouser(open_id);
            template.setPage("/pages/index/index");
            List<TemplateParam> paras = new ArrayList<TemplateParam>();
            paras.add(new TemplateParam("thing1", "申请通过"));
            paras.add(new TemplateParam("thing2", "您的申请已经通过"));
            paras.add(new TemplateParam("thing5", beizhu));
            paras.add(new TemplateParam("thing6", "店铺入住申请"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            paras.add(new TemplateParam("date4", df.format(new Date())));

            template.setTemplateParamList(paras);

            String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
            requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
            String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

            return Result.ok(reqMess);
        }

        if(status.equals("2")){
            //不通过
            //订阅消息
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
            //得到了模板id;zTNpnRfGK8YgL2i6cctWsaIdPXqUHtdk8F60fxT6RGE
            Template template = new Template();
            template.setTemplate_id("zxzluWczkU7XiiBL_gRY6VCS6Bl7jiFfQHGJ1dgA6tE");
            template.setTouser(open_id);
            template.setPage("/pages/index/index");
            List<TemplateParam> paras = new ArrayList<TemplateParam>();
            paras.add(new TemplateParam("thing1", "申请不通过"));
            paras.add(new TemplateParam("thing2", refuseMess));
            paras.add(new TemplateParam("thing5", beizhu));
            paras.add(new TemplateParam("thing6", "店铺入住申请"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            paras.add(new TemplateParam("date4", df.format(new Date())));

            template.setTemplateParamList(paras);

            String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
            requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
            String reqMess = HttpRequest.sendPost(requestUrl, template.toJSON());

            return Result.ok(reqMess);
        }

        return Result.ok("ok");
    }

    @GetMapping("/findByOpenId")
    public ShopSys findByOpenId(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String open_id = request.getParameter("open_id");

        ShopSys byOpenId = shopSysService.findByOpenId(open_id);


        return byOpenId;
    }

    @GetMapping("/findById")
    public ShopSys findById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        ShopSys byOpenId = shopSysService.findByOpenId(id);


        return byOpenId;
    }

    @GetMapping("/findShopVip")
    public List<ShopVip> findShopVip(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String shop_id = request.getParameter("shop_id");

        List<ShopVip> byShopId = shopVipService.findByShopId(shop_id);


        return byShopId;
    }

    @GetMapping("/sc")
    public Result ShopSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        if(shopScService.findByUserId(user_id,shop_id) == null){
            ShopSc shopSc = new ShopSc();

            shopSc.setUser_id(user_id);
            shopSc.setShop_id(shop_id);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            shopSc.setCreate_time(df.format(new Date()));
            shopScService.saveSc(shopSc);

            ShopSys byOpenId = shopSysService.findByOpenId(shop_id);

            String fans = byOpenId.getFans();

            int i = Integer.parseInt(fans);

            int fans_ = i + 1;

            shopSysService.updateFans(fans_+"",shop_id);

            return Result.ok("ok");
        }else {
            return Result.ok("error:已经收藏了");
        }
    }

    @GetMapping("/deleteSc")
    public void deleteSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        shopScService.deleteSc(user_id,shop_id);

        ShopSys byOpenId = shopSysService.findByOpenId(shop_id);

        String fans = byOpenId.getFans();

        int i = Integer.parseInt(fans);

        int fans_ = i - 1;

        shopSysService.updateFans(fans_+"",shop_id);
    }

    @GetMapping("/findSc")
    public Result findSc(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");
        String shop_id = request.getParameter("shop_id");

        ShopSc byUserId = shopScService.findByUserId(user_id, shop_id);

        if(byUserId == null){
            return Result.ok("no");
        }else {
            return Result.ok("yes");
        }
    }

    @GetMapping("/getShopAddStatus")
    public Result getShopAddStatus(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String user_id = request.getParameter("user_id");

        AddShop byUserId = addShopService.findByUserId(user_id);

        if (byUserId == null){
            return Result.ok("un");
        }else {
            if(byUserId.getStatus().equals("0")){
                return Result.ok("ing");
            }else if(byUserId.getStatus().equals("1")){
                return Result.ok("ok");
            }else{
                return Result.ok("fail");
            }
        }
    }

    //申请商家接口一人一商家
    @GetMapping("/save")
    public Result saveAddShop(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String name = request.getParameter("name");
        String shop_img = request.getParameter("shop_img");
        String xian_xia = request.getParameter("xian_xia");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String user_open_id = request.getParameter("user_open_id");
        String shop_mess = request.getParameter("shop_mess");
        String user_name = request.getParameter("user_name");
        String sell_goods = request.getParameter("sell_goods");
        String id_card_img_url = request.getParameter("id_card_img_url");
        String shop_zhi_zhao = request.getParameter("shop_zhi_zhao");
        String user_id = request.getParameter("user_id");
        if(addShopService.findByUserId(user_id) != null){
            return Result.ok("IsHave");
        }
        AddShop addShop = new AddShop();
        addShop.setShop_img(shop_img);
        addShop.setShop_mess(shop_mess);
        addShop.setStatus("0");
        addShop.setXian_xia(xian_xia);
        addShop.setLatitude(latitude);
        addShop.setLongitude(longitude);
        addShop.setUser_open_id(user_open_id);
        addShop.setUser_name(user_name);
        addShop.setSell_goods(sell_goods);
        addShop.setId_card_img_url(id_card_img_url);
        addShop.setShop_zhi_zhao(shop_zhi_zhao);
        addShop.setUser_id(user_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addShop.setAdd_time(df.format(new Date()));
        addShop.setName(name);
        addShopService.saveAddShop(addShop);
        logger.info("申请商家"+user_id);
        return Result.ok("saveOk");
    }
}

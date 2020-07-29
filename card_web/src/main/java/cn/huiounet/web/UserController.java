package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.utils.HttpRequest;
import cn.huiounet.pojo.utils.WxUtils;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.utils.send_message.RamNumberUtil;
import cn.huiounet.utils.send_message.SendMessageUtil;
import cn.huiounet.utils.wxPay.WeChatTool;
import cn.huiounet.utils.wxPay.WxConfig;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/save_user")
    public Result saveUser(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String code = request.getParameter("code");

        String jsonString = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", "appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.app_key + "&js_code=" + code + "&grant_type=authorization_code");

        JSONObject json = JSONObject.fromObject(jsonString);

        String open_id = json.getString("open_id");

        return Result.ok("ok");
    }

    @GetMapping("/reg_user")
    public Result reg_user(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String phone = request.getParameter("phone");
        String code_ = request.getParameter("code_");
        String code = request.getParameter("code");
        String gander = request.getParameter("gander");
        String nickName = request.getParameter("nickName");
        String avatarUrl = request.getParameter("avatarUrl");
        String password = request.getParameter("password");




        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String code_check = jedis.get(phone);

        if(code_check.equals(code_)){
            //保存
            String jsonString = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", "appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.app_key + "&js_code=" + code + "&grant_type=authorization_code");

            JSONObject json = JSONObject.fromObject(jsonString);

            String open_id = json.getString("openid");

            UserInfoSystem byPhone = userInfoService.findByPhone(phone);
            UserInfoSystem byOpenId = userInfoService.findByOpenId(open_id);
            if (byPhone != null || byOpenId !=null){
                return Result.ok("fail");
            }

            UserInfoSystem userInfoSystem = new UserInfoSystem();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userInfoSystem.setCreate_time(df.format(new Date()));
            userInfoSystem.setHead_img(avatarUrl);
            userInfoSystem.setOpen_id(open_id);
            userInfoSystem.setNick_name(nickName);
            userInfoSystem.setPhone_number(phone);
            userInfoSystem.setSex(gander);
            userInfoSystem.setPassword(password);
            userInfoSystem.setStatus("1");
            userInfoService.saveUser(userInfoSystem);

            jedis.del(phone);
            return Result.ok("ok");
        }else {
            return Result.ok("fail");
        }

    }

    @GetMapping("/reg_user_")
    public Result reg_user_(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String code = request.getParameter("code");
        String gander = request.getParameter("gander");
        String nickName = request.getParameter("nickName");
        String avatarUrl = request.getParameter("avatarUrl");
        String password = request.getParameter("password");


            //保存
        String jsonString = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", "appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.app_key + "&js_code=" + code + "&grant_type=authorization_code");

        JSONObject json = JSONObject.fromObject(jsonString);
        String open_id = json.getString("openid");
        UserInfoSystem userInfoSystem = new UserInfoSystem();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userInfoSystem.setCreate_time(df.format(new Date()));
        userInfoSystem.setHead_img(avatarUrl);
        userInfoSystem.setOpen_id(open_id);
        userInfoSystem.setNick_name(nickName);
        userInfoSystem.setStatus("1");
        userInfoSystem.setSex(gander);
        userInfoSystem.setPassword(password);

        if(userInfoService.findByOpenId(open_id) == null){
            userInfoService.saveUser(userInfoSystem);
        }

        return Result.ok("ok");

    }

    @GetMapping("/check_user")
    public String checkUser(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String code = request.getParameter("code");

        String jsonString = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", "appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.app_key + "&js_code=" + code + "&grant_type=authorization_code");

        JSONObject json = JSONObject.fromObject(jsonString);

        String open_id = json.getString("openid");

        UserInfoSystem byOpenId = userInfoService.findByOpenId(open_id);

        if(byOpenId == null){
            return "null";
        }else if(byOpenId.getStatus().equals("0")){
            return "userIsDisabled";
        }else {
            return byOpenId.getOpen_id();
        }

    }

    @GetMapping("/find_user")
    public UserInfoSystem findUser(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String open_id = request.getParameter("open_id");

        UserInfoSystem byOpenId = userInfoService.findByOpenId(open_id);

        return byOpenId;
    }

    @GetMapping("/find_userById")
    public UserInfoSystem find_userById(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");

        UserInfoSystem byId = userInfoService.findById(id);

        return byId;
    }

    @GetMapping("/update_user")
    public Result update_user(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");
        String q_m = request.getParameter("qian_ming");

       userInfoService.updateQM(q_m,id);

        return Result.ok("ok");
    }

    @GetMapping("/getMess")
    public Result getMess(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String phone = request.getParameter("phone");
        String num =  RamNumberUtil.getRandomStr(6,0);

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        jedis.set(phone,num); //存

        UserInfoSystem byPhone = userInfoService.findByPhone(phone);

        if(byPhone == null){
            try {
                SendMessageUtil.getMess(phone,num);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.ok("ok");
        }else {
            return Result.ok("fail");
        }




    }
}

package cn.huiounet.web;

import cn.huiounet.pojo.ReturnUser;
import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.UserSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.redis.RedisUtil;
import cn.huiounet.utils.send_message.RamNumberUtil;
import cn.huiounet.utils.send_message.SendMessageUtil;
import cn.huiounet.utils.wxPay.WXPayUtil;
import cn.huiounet.utils.wxPay.WeChatTool;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 系统端口
     */
    @GetMapping("findAll")
    public Map findAll(int page, int limit){
        int truePage = page - 1;
        int start = truePage * limit;
        List<UserInfoSystem> allUser = userInfoService.findAllUser(start, limit);
        logger.info(allUser.toString());
        Map map = new HashMap();
        map.put("code",0);
        map.put("data",allUser);
        return map;
    }

    @GetMapping("/login")
    public String login(String username,String password) {
        Subject subject = SecurityUtils.getSubject();
        //2 将登陆表单封装成 token 对象
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //3 让 shiro 框架进行登录验证：
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();

            return "error";
        }

        return "ok";

    }
    /**
     * 小程序控制
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/save_user")
    public Result saveUser(HttpServletResponse response, HttpServletRequest request) {
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
    public Result reg_user(HttpServletResponse response, HttpServletRequest request) {
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
        String phoneMess = request.getParameter("phoneMess");
        String from_user_id = request.getParameter("from_user_id");

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String code_check = jedis.get(phone);

        if (code_check.equals(code_)) {
            //保存
            String jsonString = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", "appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.app_key + "&js_code=" + code + "&grant_type=authorization_code");

            JSONObject json = JSONObject.fromObject(jsonString);

            String open_id = json.getString("openid");

            UserInfoSystem byPhone = userInfoService.findByPhone(phone);
            UserInfoSystem byOpenId = userInfoService.findByOpenId(open_id);
            if (byPhone != null || byOpenId != null) {
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
            userInfoSystem.setJifen("0");
            userInfoSystem.setMoney("0");
            userInfoSystem.setPhone_mess(phoneMess);
            userInfoSystem.setUser_tuijian_id(RamNumberUtil.getRandomStr(6, 1));
            userInfoSystem.setFrom_id(from_user_id);
            userInfoSystem.setStatus("1");
            //发消息
            String randomStr = RamNumberUtil.getRandomStr(4, 0);
            try {
                SendMessageUtil.getMess2(phone, randomStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userInfoSystem.setPassword(randomStr);
            userInfoService.saveUser(userInfoSystem);

            jedis.del(phone);
            return Result.ok("ok");
        } else {
            return Result.ok("fail");
        }

    }

    @GetMapping("/reg_user_")
    public Result reg_user_(HttpServletResponse response, HttpServletRequest request) {
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
        userInfoSystem.setIs_vip("0");
        userInfoSystem.setPassword(password);


        if (userInfoService.findByOpenId(open_id) == null) {
            userInfoService.saveUser(userInfoSystem);
        }

        return Result.ok("ok");

    }

    @GetMapping("/check_user")
    public String checkUser(HttpServletResponse response, HttpServletRequest request) {
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

        if (byOpenId == null) {
            return "null";
        } else if (byOpenId.getStatus().equals("0")) {
            return "userIsDisabled";
        } else {
            return byOpenId.getOpen_id();
        }

    }


    public String getToken(String userId) {

        String nonceStr = WXPayUtil.generateUUID();

        RedisUtil.redisSetString(userId, nonceStr, true, 7200000);

        return nonceStr;
    }

    @GetMapping("/find_user")
    public ReturnUser findUser(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String open_id = request.getParameter("open_id");

        UserInfoSystem byOpenId = userInfoService.findByOpenId(open_id);

        String token = getToken(byOpenId.getId() + "");

        ReturnUser returnUser = new ReturnUser(byOpenId, token);


        logger.info("用户" + open_id + "登录结果:" + returnUser);
        return returnUser;
    }

    @GetMapping("/find_userById")
    public UserInfoSystem find_userById(HttpServletResponse response, HttpServletRequest request) {
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
    public Result update_user(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");
        String q_m = request.getParameter("qian_ming");

        userInfoService.updateQM(q_m, id);

        logger.info("签名ID" + id + "被更新");

        return Result.ok("ok");
    }

    @GetMapping("/update_Pwd")
    public Result update_Pwd(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        userInfoService.updatePassWord(pwd,id);

        logger.info("密码" + id + "被更新");

        return Result.ok("ok");
    }

    @GetMapping("/find_pwd_mess")
    public Result find_pwd_mess(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String phone = request.getParameter("phone");

        String randomStr = RamNumberUtil.getRandomStr(6, 0);

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        jedis.set(phone, randomStr); //存
        try {
            SendMessageUtil.getMess3(phone, randomStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.ok("ok");
    }


    @GetMapping("/check_pwd")
    public Result check_pwd(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String phone = request.getParameter("phone");
        String check_code = request.getParameter("check_code");

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口

        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String s = jedis.get(phone);

        if (check_code.equals(s)){
            return Result.ok("ok");
        }else {
            return Result.ok("fail");
        }
    }

    @GetMapping("/regcheckcode")
    public Result regcheckcode(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String phone = request.getParameter("phone");
        String mycode = request.getParameter("mycode");

        String s = RedisUtil.redisGetString(phone + "SERVER_CODE");
        logger.info(s);
        if(s.equalsIgnoreCase(mycode)){
            //正确
            UserController userController = new UserController();
            Result mess = userController.getMess(phone);
            return mess;
        }else {
            return Result.fail("code_fail");
        }

    }

    public  Result getMess(String phone) {
        String num = RamNumberUtil.getRandomStr(6, 0);

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        jedis.set(phone, num); //存

            try {
                SendMessageUtil.getMess(phone, num);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.ok("ok");
    }
}

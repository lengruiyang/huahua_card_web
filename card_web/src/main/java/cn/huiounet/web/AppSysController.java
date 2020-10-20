package cn.huiounet.web;

import cn.huiounet.utils.access_token.GetTokenUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/app")
@RestController
public class AppSysController {
    private static final Logger logger = Logger.getLogger(AppSysController.class);

    @Autowired
    public HttpServletRequest Request;

    /**
     * token
     */
    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 0/1 * * ?")
    public void setWxToken() {

        String token_wx = GetTokenUtil.getToken_wx();

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.set("token", token_wx);

        logger.info("保存token" + token);
    }


    @GetMapping("/token")
    public String getWxToken() {

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口
        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token_wx = GetTokenUtil.getToken_wx();

        jedis.set("token", token_wx);

        return token_wx;

    }

    @GetMapping("/code_img")
    public String getImg(){
        String server_code = (String)Request.getSession().getAttribute("SERVER_CODE");

        return server_code;
    }
}

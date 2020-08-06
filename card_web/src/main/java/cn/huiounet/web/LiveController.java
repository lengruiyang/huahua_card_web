package cn.huiounet.web;

import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.live.LiveSysReturn;
import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 直播控制器
 */
@RestController
@RequestMapping("/live")
public class LiveController {

    @GetMapping("/getLiveList")
    public List<LiveSysReturn> getLiveList(HttpServletResponse response, HttpServletRequest request) {

        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String start = request.getParameter("start");
        String limit = request.getParameter("limit");


        LiveSys liveSys = new LiveSys(Integer.parseInt(start), Integer.parseInt(limit));

        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口


        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String token = jedis.get("token");

        if (token == null) {
            String token_wx = GetTokenUtil.getToken_wx();

            jedis.set("token", token_wx);

            token = jedis.get("token");
        }

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(liveSys);

        String Mess = HttpRequest.sendPost("https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=" + token, jsonObject.toJSONString());

        JSONObject jsonObject2 = JSONObject.parseObject(Mess);

        JSONArray roomList = jsonObject2.getJSONArray("room_info");

        List<LiveSysReturn> liveSysReturns = new ArrayList<>();

        for (int i = 0; i < roomList.size(); i++) {
            JSONObject jsonObject3 = (JSONObject) roomList.get(i);
            String anchor_name = jsonObject3.getString("anchor_name");
            String cover_img = jsonObject3.getString("cover_img");
            String end_time = jsonObject3.getString("end_time");
            String live_status = jsonObject3.getString("live_status");
            String name = jsonObject3.getString("name");
            String roomid = jsonObject3.getString("roomid");
            String share_img = jsonObject3.getString("share_img");
            String start_time = jsonObject3.getString("start_time");
            LiveSysReturn liveSysReturn = new LiveSysReturn();
            liveSysReturn.setAnchor_name(anchor_name);
            liveSysReturn.setCover_img(cover_img);
            liveSysReturn.setEnd_time(end_time);
            liveSysReturn.setLive_status(live_status);
            liveSysReturn.setName(name);
            liveSysReturn.setRoomid(roomid);
            liveSysReturn.setShare_img(share_img);
            liveSysReturn.setStart_time(start_time);

            liveSysReturns.add(liveSysReturn);
        }

        return liveSysReturns;

    }
}

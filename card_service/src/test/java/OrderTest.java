
import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.live.LiveSysReturn;
import cn.huiounet.service.*;

import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yd
 * @version 1.0
 * @date 2019/12/17 13:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-*.xml")
public class OrderTest {

    @Autowired
    private OrderSysService orderSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PingJiaSysService pingJiaSysService;

    @Autowired
    private PingJIaHFService pingJIaHFService;

    @Test
    public void test_(){

//        LiveSys liveSys = new LiveSys(0, 5);
//
//
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(liveSys);
//
//        String Mess = HttpRequest.sendPost("https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=36_XSvfwduYlcyZh6BIOIA7uYqHIiH0s21QHBELhUiROBxEjwwz0B_gckLw3p60xL3iY5ZmLkf1G29goC2anBucjvkAtqOlJWPe7GpKUU6-X6jtcQRVF9Ge7MzNbmWRZhfxyrlXTXggekRLICQFHJTgAIABDN" , jsonObject.toJSONString());
//
//        System.out.println(Mess);
//        JSONObject jsonObject2 = JSONObject.parseObject(Mess);
//
//        JSONArray roomList = jsonObject2.getJSONArray("room_info");
//
//        List<LiveSysReturn> liveSysReturns = new ArrayList<>();
//
//        System.out.println(roomList);
//
//        for (int i = 0; i < roomList.size(); i++) {
//            JSONObject jsonObject3 = (JSONObject) roomList.get(i);
//            String anchor_name = jsonObject3.getString("anchor_name");
//            String cover_img = jsonObject3.getString("cover_img");
//            String end_time = jsonObject3.getString("end_time");
//            String live_status = jsonObject3.getString("live_status");
//            String name = jsonObject3.getString("name");
//            String roomid = jsonObject3.getString("roomid");
//            String share_img = jsonObject3.getString("share_img");
//            String start_time = jsonObject3.getString("start_time");
//            LiveSysReturn liveSysReturn = new LiveSysReturn();
//            liveSysReturn.setAnchor_name(anchor_name);
//            liveSysReturn.setCover_img(cover_img);
//            liveSysReturn.setEnd_time(end_time);
//            liveSysReturn.setLive_status(live_status);
//            liveSysReturn.setName(name);
//            liveSysReturn.setRoomid(roomid);
//            liveSysReturn.setShare_img(share_img);
//            liveSysReturn.setStart_time(start_time);
//
//            liveSysReturns.add(liveSysReturn);
//        }
//
//        System.out.println(liveSysReturns.toString());
    }




}

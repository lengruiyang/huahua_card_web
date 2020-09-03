
import cn.huiounet.pojo.goods.GoodsSys;
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

    }




}

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.address.AddressSys;
import cn.huiounet.pojo.address.AddressTip;
import cn.huiounet.pojo.cart.CartSys;
import cn.huiounet.pojo.dingyuexiaoxi.Template;
import cn.huiounet.pojo.dingyuexiaoxi.TemplateParam;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.pingjia.PingJiaSys;
import cn.huiounet.service.*;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.http.HttpUtils;
import cn.huiounet.utils.send_message.RamNumberUtil;
import cn.huiounet.utils.send_message.SendMessageUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.apache.commons.codec.binary.Base64.encodeBase64;


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
    private CartSysService cartSysService;

    @Test
    public void test_(){

//
//        System.out.println(cartSys.toString());
//        goodsSysService.updateLike("1","1");
//        goodsSysService.updateSell_many("1","1");
//
//        List<PingJiaSys> pingJiaSys = pingJiaSysService.PoorPj();
//
//        System.out.println(pingJiaSys.toString());
        //orderAddressService.updateByOrderNum("1","1","1","862288f563284b27b0f9592b7d2af281");

    }




}

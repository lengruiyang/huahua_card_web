
import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.coupon.CouPon;
import cn.huiounet.pojo.coupon.CouponSys;
import cn.huiounet.pojo.coupon.ReturnCoupon;
import cn.huiounet.pojo.daohang.DaoHangSys;
import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.live.LiveSysReturn;
import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.root.GoodsRoot;
import cn.huiounet.pojo.search.SearchCount;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;

import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.juhe.JuHeCzUtils;
import cn.huiounet.utils.math.Arith;
import cn.huiounet.utils.phone.PhoneUtils;
import cn.huiounet.utils.qrcode.GetImage;
import cn.huiounet.utils.qrcode.ImageQrcode;
import cn.huiounet.utils.qrcode.QRCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private CouponSysService couponSysService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GoodsRootService goodsRootService;

    @Autowired
    private GoodsColorService goodsColorService;

    @Autowired
    private ImgSysService imgSysService;

    @Autowired
    private HuaFeiOrderSysService huaFeiOrderSysService;

    @Autowired
    private DaoHangSysService daoHangSysService;

    @Autowired
    private MiaoShaGoodsSysService miaoShaGoodsSysService;
    private static String fileUrl = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/";
    @Test
    public void test_() throws Exception {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = sdf.parse("2020-11-30 00:00");
//        long longDate = date.getTime();
//        int id = 15;
//        for (int i = 0; i < 11; i++) {
//            MiaoShaGoodsSys byId = miaoShaGoodsSysService.findById(id + "");
//            miaoShaGoodsSysService.updateStartTime(longDate + "", byId.getId() + "");
//            longDate += 7200000;
//            id+=1;
//        }
//        Long payMoneyNum = orderSysService.findPayMoneyNum();
//        double sub = Arith.sub(Double.valueOf(payMoneyNum), Double.valueOf(100));
//        System.out.println(sub);
//        ArrayList<String> pastDaysList = new ArrayList<>();
//
//        for (int i = 0; i <7; i++) {
//            pastDaysList.add(getPastDate(i));
//            String pastDate = getPastDate(i);
//            List<OrderSys> byData = orderSysService.findByData(pastDate);
//            System.out.println("日期:"+pastDate+"订单:"+byData.size());
//        }


//        System.out.println(pastDaysList.toString());
    }

//    public static String getPastDate(int past) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
//        Date today = calendar.getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String result = format.format(today);
//
//        return result;
//    }


}

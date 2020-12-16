
import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.coupon.CouPon;
import cn.huiounet.pojo.coupon.CouponSys;
import cn.huiounet.pojo.coupon.ReturnCoupon;
import cn.huiounet.pojo.daohang.DaoHangSys;
import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiFarther;
import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiSon;
import cn.huiounet.pojo.diandan.ReturnFenLei;
import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.live.GetLiveLogsPojo;
import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.live.LiveSysReturn;
import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.pojo.order.ZhuanZhangOrder;
import cn.huiounet.pojo.root.GoodsRoot;
import cn.huiounet.pojo.search.SearchCount;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;

import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.elasticsearch.AbstractJunitTest;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.juhe.JuHeCzUtils;
import cn.huiounet.utils.math.Arith;
import cn.huiounet.utils.phone.PhoneUtils;
import cn.huiounet.utils.qrcode.GetImage;
import cn.huiounet.utils.qrcode.ImageQrcode;
import cn.huiounet.utils.qrcode.QRCodeUtil;
import cn.huiounet.utils.wxUtils.WxUpload;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
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
import java.net.InetAddress;
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
    private DianDanGoodsFenLeiSonService dianDanGoodsFenLeiSonService;
    @Autowired
    private DianDanGoodsFenLeiFartherService dianDanGoodsFenLeiFartherService;

    @Autowired
    private ZhuanZhangOrderService zhuanZhangOrderService;

    @Autowired
    private LiveService liveService;

    @Autowired
    private MiaoShaGoodsSysService miaoShaGoodsSysService;
    private static String fileUrl = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/";
    @Test
    public void test_() throws Exception {
        GetLiveLogsPojo getLiveLogsPojo = new GetLiveLogsPojo();
        getLiveLogsPojo.setRoom_id(2);
        getLiveLogsPojo.setAction("get_replay");
        getLiveLogsPojo.setLimit(10);
        getLiveLogsPojo.setStart(0);

        String liveLogs = liveService.getLiveLogs(getLiveLogsPojo);
        System.out.println(liveLogs);
    }




}

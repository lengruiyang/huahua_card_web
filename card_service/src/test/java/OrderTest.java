
import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.coupon.CouPon;
import cn.huiounet.pojo.coupon.CouponSys;
import cn.huiounet.pojo.coupon.ReturnCoupon;
import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.live.LiveSysReturn;
import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import cn.huiounet.pojo.search.SearchCount;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;

import cn.huiounet.utils.access_token.GetTokenUtil;
import cn.huiounet.utils.http.HttpRequest;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private CouPonService couPonService;

    @Test
    public void test_()throws Exception{
        UserInfoSystem byId = userInfoService.findById("7");
        System.out.println(byId.toString());
//        List<CouPon> bySys  = couPonService.findBySysGoods("1");
//        System.out.println(bySys.toString());
//        UserInfoSystem byId1 = userInfoService.findById("7");
//        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d3 = df2.parse(byId1.getCreate_time());
//        long time = d3.getTime();
//        long l = System.currentTimeMillis();
//        System.out.println(time+604800000);
//        System.out.println(l);
//        List<ReturnCoupon> returnCoupons = new ArrayList<>();

//        if (byId.getIs_vip().equals("0")) {
//            List<CouPon> bySys  = couPonService.findBySys();
//            System.out.println(bySys.toString());
//            for(int i = 0;i<bySys.size();i++){
//                ReturnCoupon returnCoupon = new ReturnCoupon();
//                CouPon couPon = bySys.get(i);
//                int id = couPon.getId();
//                CouponSys byIdAndUser = couponSysService.findByIdAndUser(id+"", "7");
//                if(byIdAndUser == null){
//                    returnCoupon.setIs_get("no");
//                }else {
//                    returnCoupon.setIs_get("yes");
//                }
//                returnCoupon.setCouPon(couPon);
//                returnCoupons.add(returnCoupon);
//            }
//
//        } else if (byId.getIs_vip().equals("1")) {
            //是会员
//
//            List<CouPon> bySys  = couPonService.findBySys();
//            for(int i = 0;i<bySys.size();i++){
//                ReturnCoupon returnCoupon = new ReturnCoupon();
//                CouPon couPon = bySys.get(i);
//                int id = couPon.getId();
//                CouponSys byIdAndUser = couponSysService.findByIdAndUser(id+"", "7");
//                if(byIdAndUser == null){
//                    returnCoupon.setIs_get("no");
//                }else {
//                    returnCoupon.setIs_get("yes");
//                }
//                returnCoupon.setCouPon(couPon);
//                returnCoupons.add(returnCoupon);
//            }
//        System.out.println(returnCoupons.toString());
//        SearchCount searchCount = new SearchCount();
//        searchCount.setSearch_mess("77");
//        searchCount.setCount("1");
//
//        searchCountService.saveSearchMess(searchCount);
//        List<GoodsSys> goodsSys = goodsSysService.searchGoods("衣服", 0, 10);
//
//        System.out.println(goodsSys.toString());
//        String input = "裤子";
//// 需要爬取商品信息的网站地址
//        String url = "https://list.tmall.com/search_product.htm?q=" + input;
//// 动态模拟请求数据
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//// 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
//        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
//        CloseableHttpResponse response = httpclient.execute(httpGet);
//// 获取响应状态码
//        int statusCode = response.getStatusLine().getStatusCode();
//        System.out.println(statusCode);
//        try {
//            HttpEntity entity = response.getEntity();
//            // 如果状态响应码为200，则获取html实体内容或者json文件
//            if(statusCode == 200){
//                String html = EntityUtils.toString(entity, Consts.UTF_8);
//                // 提取HTML得到商品信息结果
//                Document doc = null;
//                // doc获取整个页面的所有数据
//                doc = Jsoup.parse(html);
//                //输出doc可以看到所获取到的页面源代码
//      //System.out.println(doc);
//                // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
//                Elements ulList = doc.select("div[class='product']");
//                Elements liList = ulList.select("div[class='product-iWrap']");
//                // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
//                int count = 5000;
//                for (Element item : liList) {
//                    GoodsSys goodsSys = new GoodsSys();
//                    ImgSys imgSys = new ImgSys();
//                    GoodsSize goodsSize = new GoodsSize();
//                    GoodsColor goodsColor = new GoodsColor();
//                    // 商品ID
//                    String id = item.select("div[class='product']").select("p[class='productStatus']").select("span[class='ww-light ww-small m_wangwang J_WangWang']").attr("data-item");
//                    //System.out.println("商品ID："+id);
//                    // 商品名称
//                    String name = item.select("p[class='productTitle']").select("a").attr("title");
//                    //System.out.println("商品名称："+name);
//                    // 商品价格
//                    String price = item.select("p[class='productPrice']").select("em").attr("title");
//                    //System.out.println("商品价格："+price);
//                    // 商品网址
//                    String goodsUrl = item.select("p[class='productTitle']").select("a").attr("href");
//                    //System.out.println("商品网址："+goodsUrl);
//                    // 商品图片网址
//                    String imgUrl = item.select("div[class='productImg-wrap']").select("a").select("img").attr("data-ks-lazyload");
//                    //System.out.println("商品图片网址："+imgUrl.replace("//",""));
//                    count+=1;
//                    int maxId2 = goodsSysService.findMaxId();
//                    goodsSys.setGoods_cen_img("http://"+imgUrl.replace("//",""));
//                    goodsSys.setGoods_name(name);
//                    goodsSys.setGet_score("5");
//                    goodsSys.setKucun("1000");
//                    goodsSys.setIs_miaosha("0");
//
//                    goodsSys.setLike_many(count+"");
//                    goodsSys.setSell_many(count+"");
//                    goodsSys.setYun_fei("0");
//                    goodsSys.setTip("测试");
//                    System.out.println(price+"价格");
//                    String[] split = price.split("\\.");
//                    goodsSys.setSc_price(price);
//                    goodsSys.setWhere_from("江西");
//                    if(maxId2%2 == 0){
//                        goodsSys.setShop_id("oyr6B4nluh0pxBk6sxBGvtOcJOxY");
//                    }else {
//                        goodsSys.setShop_id("oyr6B4hEfuDVsju1_xyQBiuMlyWM");
//                    }
//                    goodsSys.setSecond_name("测试商品");
//                    System.out.println(Arrays.toString(split));
//                    goodsSys.setLow_price(split[0]);
//                    goodsSys.setStatus("1");
//                    goodsSys.setHight_price(split[1]);
//                    goodsSysService.saveGoodsSys(goodsSys);
//                    int maxId = goodsSysService.findMaxId();
//                    imgSys.setGoods_id(maxId+"");
//                    imgSys.setStatus("1");
//                    imgSys.setUrl("http://"+imgUrl.replace("//",""));
//                    imgSysService.saveImg(imgSys);
//                    goodsColor.setGoods_id(maxId+"");
//                    goodsColor.setColor("测试");
//                    goodsColor.setStatus("1");
//                    goodsColor.setImg("http://"+imgUrl.replace("//",""));
//                    goodsColorService.saveGoodsColor(goodsColor);
//                    int maxId1 = goodsColorService.findMaxId();
//                    goodsSize.setColor_id(maxId1+"");
//                    goodsSize.setCompany("条");
//                    goodsSize.setSize("1");
//                    goodsSize.setStatus("1");
//                    goodsSize.setPrice("0.01");
//                    goodsSizeService.saveSize(goodsSize);
//                }
//                // 消耗掉实体
//                EntityUtils.consume(response.getEntity());
//            } else {
//                // 消耗掉实体
//                EntityUtils.consume(response.getEntity());
//            }
//        } finally {
//            response.close();
//        }

    }




}

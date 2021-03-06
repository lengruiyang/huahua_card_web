package cn.huiounet.web;

import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.*;
import cn.huiounet.utils.http.HttpRequest;


import cn.huiounet.utils.tuikuan.TuiKuanSys;
import cn.huiounet.utils.wxPay.WXPayUtil;
import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 获取商品机器人
 */
@RestController
@RequestMapping("/rabbit")
public class GoodsRabbit {
    private static final Logger logger = Logger.getLogger(GoodsRabbit.class);
    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private GoodsColorService goodsColorService;

    @Autowired
    private ImgSysService imgSysService;

    @Autowired
    private HuaFeiOrderSysService huaFeiOrderSysService;

    @Autowired
    private GoodsSizeService goodsSizeService;

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 */1 * * * ?")
    public void getTKOuTo(){
        logger.info("拼多多执行");
        String s = HttpRequest.sendGet("https://huahuaka.huiounet.cn/app/index.php", "i=2&c=entry&do=getDdOrderList&m=first_duoduoke");
        logger.info("拼多多执行"+s);
        logger.info("拼多多2执行");
        String s2 = HttpRequest.sendGet("https://huahuaka.huiounet.cn/app/index.php", "i=2&c=entry&do=getDdOrderDetail&m=first_duoduoke");
        logger.info("拼多多2执行"+s2);
        String s3 = HttpRequest.sendGet("https://huahuaka.huiounet.cn/app/index.php", "i=2&c=entry&do=getJdOrderList&m=first_duoduoke");
        logger.info("京东执行"+s3);


    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 */1 * * * ?")
    public void rabbitGoods() throws Exception {
        logger.info("执行订单查询");
        List<HuaFeiOrderSys> payButNotSuccess = huaFeiOrderSysService.findPayButNotSuccess("0");
        for(int i = 0;i<payButNotSuccess.size();i++){
            HuaFeiOrderSys huaFeiOrderSys = payButNotSuccess.get(i);
            String order_num = huaFeiOrderSys.getOrder_num();
            String s = HttpRequest.sendGet("http://op.juhe.cn/ofpay/mobile/ordersta", "orderid=" + order_num + "&key=6804dadc2cf950de47c3ee0120d79cab");
            JSONObject json = JSONObject.fromObject(s);
            logger.info(json);
            JSONObject result = json.getJSONObject("result");
            String game_state = result.getString("game_state");
            if(game_state.equals("1")){
                logger.info(order_num+"充值成功");
                huaFeiOrderSysService.updateById("1",payButNotSuccess.get(i).getId()+"");
            }else if(game_state.equals("0")){
                huaFeiOrderSysService.updateById("0",payButNotSuccess.get(i).getId()+"");
                logger.info(order_num+"充值中");
            }else {
                huaFeiOrderSysService.updateById("2",payButNotSuccess.get(i).getId()+"");
                logger.info(order_num+"充值失败");
                String nonceStr = WXPayUtil.generateUUID(); //订单号
                String pay_money = huaFeiOrderSys.getPay_money();
                String tuiKuan = TuiKuanSys.tuiKuan(order_num, nonceStr, Integer.parseInt(pay_money), Integer.parseInt(pay_money), "话费充值退款");

                logger.info("退款详情:"+tuiKuan);
            }
        }

    }

    @GetMapping("/getGoods")
    public Result getGoods(String input,String num)throws Exception{
// 需要爬取商品信息的网站地址
        String url = "https://list.tmall.com/search_product.htm?q=" + input;
// 动态模拟请求数据
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
// 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
// 获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        try {
            HttpEntity entity = response.getEntity();
            // 如果状态响应码为200，则获取html实体内容或者json文件
            if(statusCode == 200){
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                // 提取HTML得到商品信息结果
                Document doc = null;
                // doc获取整个页面的所有数据
                doc = Jsoup.parse(html);
                //输出doc可以看到所获取到的页面源代码
                //System.out.println(doc);
                // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                Elements ulList = doc.select("div[class='product']");
                Elements liList = ulList.select("div[class='product-iWrap']");
                // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                int i = Integer.parseInt(num);
                int count = i;
                for (Element item : liList) {
                    GoodsSys goodsSys = new GoodsSys();
                    ImgSys imgSys = new ImgSys();
                    GoodsSize goodsSize = new GoodsSize();
                    GoodsColor goodsColor = new GoodsColor();
                    // 商品ID
                    String id = item.select("div[class='product']").select("p[class='productStatus']").select("span[class='ww-light ww-small m_wangwang J_WangWang']").attr("data-item");
                    //System.out.println("商品ID："+id);
                    // 商品名称
                    String name = item.select("p[class='productTitle']").select("a").attr("title");
                    //System.out.println("商品名称："+name);
                    // 商品价格
                    String price = item.select("p[class='productPrice']").select("em").attr("title");
                    //System.out.println("商品价格："+price);
                    // 商品网址
                    String goodsUrl = item.select("p[class='productTitle']").select("a").attr("href");
                    //System.out.println("商品网址："+goodsUrl);
                    // 商品图片网址
                    String imgUrl = item.select("div[class='productImg-wrap']").select("a").select("img").attr("data-ks-lazyload");
                    //System.out.println("商品图片网址："+imgUrl.replace("//",""));
                    count+=1;
                    int maxId2 = goodsSysService.findMaxId();

                    goodsSys.setGoods_cen_img("http://"+imgUrl.replace("//",""));
                    goodsSys.setGoods_name(name);
                    goodsSys.setGet_score("5");
                    goodsSys.setKucun("1000");
                    goodsSys.setIs_miaosha("0");
                    goodsSys.setLike_many(count+"");
                    goodsSys.setSell_many(count+"");
                    goodsSys.setYun_fei("0");
                    goodsSys.setTip("测试");


                    String[] split = price.split("\\.");
                    goodsSys.setSc_price(price);
                    goodsSys.setWhere_from("江西");
                    if(maxId2%2 == 0){
                        goodsSys.setShop_id("oyr6B4nluh0pxBk6sxBGvtOcJOxY");
                    }else {
                        goodsSys.setShop_id("oyr6B4hEfuDVsju1_xyQBiuMlyWM");
                    }
                    goodsSys.setSecond_name("测试商品");
                    goodsSys.setLow_price(split[0]);
                    goodsSys.setStatus("1");
                    goodsSys.setHight_price(split[1]);
                    if(goodsSys.getGoods_cen_img().equals("http://")){
                        continue;
                    }
                    goodsSysService.saveGoodsSys(goodsSys);
                    int maxId = goodsSysService.findMaxId();
                    imgSys.setGoods_id(maxId+"");
                    imgSys.setStatus("1");
                    imgSys.setUrl("http://"+imgUrl.replace("//",""));
                    imgSysService.saveImg(imgSys);
                    goodsColor.setGoods_id(maxId+"");
                    goodsColor.setColor("测试");
                    goodsColor.setStatus("1");
                    goodsColor.setImg("http://"+imgUrl.replace("//",""));
                    goodsColorService.saveGoodsColor(goodsColor);
                    int maxId1 = goodsColorService.findMaxId();
                    goodsSize.setColor_id(maxId1+"");
                    goodsSize.setCompany("条");
                    goodsSize.setSize("1");
                    goodsSize.setStatus("1");
                    goodsSize.setPrice("0.01");
                    goodsSizeService.saveSize(goodsSize);
                }
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            } else {
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            }
        } finally {
            response.close();
        }

        return Result.ok("状态码:"+statusCode);
    }
}

package cn.huiounet.utils.create_order;

import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.wxPay.WXPayUtil;
import cn.huiounet.utils.wxPay.WeChatTool;
import cn.huiounet.utils.xml.XmlPayUtil;

import java.util.HashMap;
import java.util.Map;

public class CreateOrder {

    /**
     * 微信支付统一下单接口
     * @param goods_name 物品名称
     * @param openid 用户唯一id
     * @param out_trade_no 自定义订单号
     * @param money 支付零钱 /分
     * @return
     */
    public static Map<String, String> createOrder(String goods_name, String openid, String out_trade_no, int money) {

        String mch_id = WeChatTool.mch_id; //商户号


        Map<String, String> result = new HashMap<String, String>();


        XmlPayUtil xmlController = new XmlPayUtil();

        String getopenid = xmlController.PayXml(goods_name,openid, out_trade_no, money);


        //在servlet层中生成签名成功后，把下单所要的参数以xml的格式拼接，发送下单接口
        String httpResult = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", getopenid);

        try {
            //xml转换成Map对象或者值
            Map<String, String> resultMap = WXPayUtil.xmlToMap(httpResult);
            result.put("package", "prepay_id=" + resultMap.get("prepay_id")); //这里是拿下单成功的微信交易号去拼接，因为在下面的接口中必须要这个样子
            result.put("nonceStr", resultMap.get("nonce_str")); //随机字符串
        } catch (Exception e) {
            e.printStackTrace();
        }

        String times = WXPayUtil.getCurrentTimestamp() + ""; //获取当前时间
        result.put("timeStamp", times); //当前时间戳
        //生成调用支付接口要的签名
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appId", WeChatTool.wxspAppid);
        packageParams.put("signType", WeChatTool.sign_type);
        packageParams.put("nonceStr", result.get("nonceStr") + "");
        packageParams.put("timeStamp", times);
        packageParams.put("package", result.get("package") + "");//商户订单号
        String sign = "";
        try {
            sign = WXPayUtil.generateSignature(packageParams, WeChatTool.sercet_key); //生成签名:

        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("paySign", sign);
        result.put("ordernum_mine", out_trade_no);

        return result; //所有的参数放进map中保存发送到小程序页面中,去调用微信支付接口
    }

}

package cn.huiounet.utils.wxPay;

/**
 * @author yd
 * @version 1.0
 * @date 2020/1/6 00:35
 */
public  class WeChatTool {

    public static String wxspAppid = WxConfig.getConfig().getAppid(); //公众号id

    public static String mch_id = WxConfig.getConfig().getWxpayid(); //商户

    public static String notify_url="https://xcx2.huiounet.cn/card_web_/pay/callBack.lry";//异步地址

    public static String trade_type = "JSAPI"; //小程序

    public static String sign_type = "MD5";

    public static String sercet_key = WxConfig.getConfig().getKey();

    public static String app_key = WxConfig.getConfig().getAppkey();
}

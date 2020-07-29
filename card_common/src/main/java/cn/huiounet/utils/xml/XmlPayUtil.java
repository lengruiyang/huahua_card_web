package cn.huiounet.utils.xml;

import cn.huiounet.utils.wxPay.WXPayUtil;
import cn.huiounet.utils.wxPay.WeChatTool;

import java.util.HashMap;
import java.util.Map;

public class XmlPayUtil {

    /**
     * 支付数据
     * @param goods_name 商品名长度限制
     * @param openid 用户openid
     * @param out_trade_no 自定义单号
     * @param total_fee 金额 /分
     * @return
     */
    public String PayXml(String goods_name,String openid,String out_trade_no,int total_fee) {

        //下单的金额，因为在微信支付中默认是分所以要这样处理
        Integer total_fees= total_fee;
//            微信下单的金额是String类型的所以要转换类型
        String money=total_fees.toString();
        String nonceStr= WXPayUtil.generateUUID(); //设置UUID作为随机字符串

        Map<String ,String> map = new HashMap<String ,String>();
        map.put("appid", WeChatTool.wxspAppid); //appid
        map.put("mch_id", WeChatTool.mch_id);//商户号
        map.put("nonce_str",nonceStr); //随机数
        map.put("body",goods_name);//商户名称
        map.put("out_trade_no",out_trade_no);//商户订单号
        map.put("total_fee",money);//下单金额
        map.put("spbill_create_ip", "127.0.0.1");//终端IP
        map.put("notify_url",WeChatTool.notify_url);//回调地址 这里的接口必须是在线上用户支付成功才能收到微信发送的信息
        map.put("trade_type","JSAPI");//交易类型
        map.put("openid",openid+"");//用户openid
        map.put("sign_type","MD5");//加密类型
        String sign="";
        try {
            sign= WXPayUtil.generateSignature(map, WeChatTool.sercet_key); //生成sign签名WeChatTool.sercet_key是商户的支付秘钥
        } catch (Exception e) {
            e.printStackTrace();
        }

        //拼接成xml的格式，这里的参数必须要和上面的一致，并且每次下单的订单号不能一致
        String formData="<xml>";

        formData += "<appid>"+ WeChatTool.wxspAppid+"</appid>";
        formData += "<mch_id>"+ WeChatTool.mch_id+"</mch_id>";
        formData += "<nonce_str>"+nonceStr+"</nonce_str>"; //随机数
        formData += "<body>"+goods_name+"</body>";
        formData += "<out_trade_no>"+out_trade_no +"</out_trade_no>"; //商户订单号
        formData += "<total_fee>"+money+"</total_fee>"; //付费的钱int (分)
        formData += "<spbill_create_ip>"+"127.0.0.1"+"</spbill_create_ip>";
        formData += "<notify_url>"+WeChatTool.notify_url+"</notify_url>";
        formData += "<trade_type>"+WeChatTool.trade_type+"</trade_type>";
        formData += "<openid>"+openid+"</openid>"; //用户id
        formData += "<sign_type>"+WeChatTool.sign_type+"</sign_type>";
        formData += "<sign>"+sign+"</sign>"; //签名算法
        formData += "</xml>";
        return formData;
    }

    /**
     * 支付状态查询
     * @param out_trade_no 支付单号
     * @return
     */
    public String psyStatusXml(String out_trade_no){
        String nonceStr= WXPayUtil.generateUUID();
        Map<String ,String> map = new HashMap<String ,String>();
        map.put("appid",WeChatTool.wxspAppid);
        map.put("mch_id",WeChatTool.mch_id);
        map.put("nonce_str",nonceStr);
        map.put("out_trade_no",out_trade_no);
        String sign="";
        try {
            sign= WXPayUtil.generateSignature(map, WeChatTool.sercet_key); //生成sign签名WeChatTool.sercet_key是商户的支付秘钥
        } catch (Exception e) {
            e.printStackTrace();
        }
        //拼接成xml的格式，这里的参数必须要和上面的一致，并且每次下单的订单号不能一致
        String formData="<xml>";
        formData += "<appid>"+ WeChatTool.wxspAppid+"</appid>";
        formData += "<mch_id>"+ WeChatTool.mch_id+"</mch_id>";
        formData += "<nonce_str>"+ nonceStr+"</nonce_str>";
        formData += "<out_trade_no>"+ out_trade_no+"</out_trade_no>";
        formData += "<sign>"+sign+"</sign>";
        formData += "</xml>";

        System.out.println(formData);
        return formData;

    }

    /**
     * 退款请求参数
     * @param out_trade_no
     * @param out_refund_no
     * @param total_fee
     * @param refund_fee
     * @param refund_desc
     * @return
     */
    public String tuiKuan(String out_trade_no,String out_refund_no,int total_fee,int refund_fee,String refund_desc){
        String nonceStr= WXPayUtil.generateUUID();
        Map<String ,String> map = new HashMap<String ,String>();
        map.put("appid",WeChatTool.wxspAppid);
        map.put("mch_id",WeChatTool.mch_id);
        map.put("nonce_str",nonceStr);
        map.put("out_trade_no",out_trade_no);
        map.put("out_refund_no",out_refund_no);
        map.put("total_fee",total_fee+"");
        map.put("refund_fee",refund_fee+"");
        map.put("refund_desc",refund_desc);
        String sign="";
        try {
            sign= WXPayUtil.generateSignature(map, WeChatTool.sercet_key); //生成sign签名WeChatTool.sercet_key是商户的支付秘钥
        } catch (Exception e) {
            e.printStackTrace();
        } //得到的签名
        String formData="<xml>";
        formData += "<appid>"+ WeChatTool.wxspAppid+"</appid>";
        formData += "<mch_id>"+ WeChatTool.mch_id+"</mch_id>";
        formData += "<nonce_str>"+ nonceStr+"</nonce_str>";
        formData += "<out_trade_no>"+ out_trade_no+"</out_trade_no>";
        formData += "<out_refund_no>"+ out_refund_no+"</out_refund_no>";
        formData += "<total_fee>"+ total_fee+"</total_fee>";
        formData += "<refund_fee>"+ refund_fee+"</refund_fee>";
        formData += "<refund_desc>"+ refund_desc+"</refund_desc>";
        formData += "<sign>"+sign+"</sign>";
        formData += "</xml>";
        return formData;
    }


    /**
     * 发起企业付款
     * @param partner_trade_no 自定义订单号
     * @param openid 用户唯一id
     * @param amount 金额
     * @param desc 说明
     * @return
     */
    public static String QiYeFu_kuan(String partner_trade_no,String openid,int amount, String desc){
        String nonceStr= WXPayUtil.generateUUID();
        Map<String ,String> map = new HashMap<String ,String>();
        map.put("mch_appid",WeChatTool.wxspAppid);
        map.put("mchid",WeChatTool.mch_id);
        map.put("nonce_str",nonceStr);
        map.put("partner_trade_no",partner_trade_no);
        map.put("openid",openid);
        map.put("check_name","NO_CHECK");
        map.put("amount",amount+"");
        map.put("desc",desc);
        String sign="";
        try {
            sign= WXPayUtil.generateSignature(map, WeChatTool.sercet_key); //生成sign签名WeChatTool.sercet_key是商户的支付秘钥
        } catch (Exception e) {
            e.printStackTrace();
        } //得到的签名
        String formData="<xml>";
        formData += "<mch_appid>"+ WeChatTool.wxspAppid+"</mch_appid>";
        formData += "<mchid>"+ WeChatTool.mch_id+"</mchid>";
        formData += "<nonce_str>"+nonceStr+"</nonce_str>";
        formData += "<partner_trade_no>"+partner_trade_no+"</partner_trade_no>";
        formData += "<openid>"+openid+"</openid>";
        formData += "<check_name>"+"NO_CHECK"+"</check_name>";
        formData += "<amount>"+amount+"</amount>";
        formData += "<desc>"+desc+"</desc>";
        formData += "<sign>"+sign+"</sign>";
        formData += "</xml>";
        return formData;
    }
}

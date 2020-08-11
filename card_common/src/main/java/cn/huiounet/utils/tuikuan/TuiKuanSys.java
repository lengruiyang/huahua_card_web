package cn.huiounet.utils.tuikuan;


import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.xml.XmlPayUtil;

public class TuiKuanSys {
    /**
     * 退款
     * @param refund_desc 退款信息
     * @param out_refund_no 退款单号
     * @param total_fee 付的钱
     * @param refund_fee 退的钱
     * @param out_trade_no 商户单号
     */
    public static String tuiKuan(String out_trade_no,String out_refund_no,int total_fee,int refund_fee,String refund_desc) {
        String wx_url="https://api.mch.weixin.qq.com/secapi/pay/refund"; //请求地址
        XmlPayUtil xmlController = new XmlPayUtil();
        String xmlMess = XmlPayUtil.tuiKuan(out_trade_no, out_refund_no, total_fee, refund_fee, refund_desc);

        String mess = "";
        try {
            mess = HttpRequest.doRefund(wx_url, xmlMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mess;
    }
}

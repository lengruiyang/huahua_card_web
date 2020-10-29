package cn.huiounet.pojo.huafei;

import java.io.Serializable;

/**
 * @author yd
 * @version 1.0
 * @date 2020/1/5 21:21
 */
public class HuaFei implements Serializable {
    private String phoneno;
    private String cardnum; //充值金额
    private String orderid; //商户id我生成
    private String key;
    private String sign; //校验值，md5(OpenID+key+phoneno+cardnum+orderid)，OpenID在个人中心查询
    private String openid;

    public HuaFei() {
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "HuaFei{" +
                "phoneno='" + phoneno + '\'' +
                ", cardnum='" + cardnum + '\'' +
                ", orderid='" + orderid + '\'' +
                ", key='" + key + '\'' +
                ", sign='" + sign + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}

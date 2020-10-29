package cn.huiounet.pojo.huafei;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_huafei_sys")
public class HuaFeiSys implements Serializable{
    private int id;
    private String status;
    private String price;
    private String pay_money;
    private String is_vip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

    public HuaFeiSys() {
    }
}

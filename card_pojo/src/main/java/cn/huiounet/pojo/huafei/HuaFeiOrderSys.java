package cn.huiounet.pojo.huafei;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_huafei_order")
public class HuaFeiOrderSys implements Serializable {
    private int id;
    private String order_num;
    private String pay_status;
    private String create_time;
    private String pay_time;
    private String pay_money;
    private String user_id;
    private String cz_phone;
    private String fuwushang; //1移动 2：电信 3：联通
    private String is_cz_success; //1：成功 2 ：失败 0：充值中

    public String getFuwushang() {
        return fuwushang;
    }

    public String getIs_cz_success() {
        return is_cz_success;
    }

    public void setIs_cz_success(String is_cz_success) {
        this.is_cz_success = is_cz_success;
    }

    @Override
    public String toString() {
        return "HuaFeiOrderSys{" +
                "id=" + id +
                ", order_num='" + order_num + '\'' +
                ", pay_status='" + pay_status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", pay_money='" + pay_money + '\'' +
                ", user_id='" + user_id + '\'' +
                ", cz_phone='" + cz_phone + '\'' +
                ", fuwushang='" + fuwushang + '\'' +
                ", is_cz_success='" + is_cz_success + '\'' +
                ", cz_much='" + cz_much + '\'' +
                '}';
    }

    public void setFuwushang(String fuwushang) {
        this.fuwushang = fuwushang;
    }

    public String getCz_much() {
        return cz_much;
    }

    public void setCz_much(String cz_much) {
        this.cz_much = cz_much;
    }

    private String cz_much;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCz_phone() {
        return cz_phone;
    }

    public void setCz_phone(String cz_phone) {
        this.cz_phone = cz_phone;
    }

    public HuaFeiOrderSys() {
    }
}

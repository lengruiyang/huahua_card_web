package cn.huiounet.pojo.order;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_order_sys")
public class OrderSys implements Serializable{
    private int id;
    private String creat_time;
    private String user_id;
    private String pay_status;
    private String order_num; //订单号
    private String goods_id;
    private String goods_notic;
    private String all_money;
    private String address_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_notic() {
        return goods_notic;
    }

    public void setGoods_notic(String goods_notic) {
        this.goods_notic = goods_notic;
    }

    public String getAll_money() {
        return all_money;
    }

    public void setAll_money(String all_money) {
        this.all_money = all_money;
    }

    public String getAddress_num() {
        return address_num;
    }

    public void setAddress_num(String address_num) {
        this.address_num = address_num;
    }

    public OrderSys() {
    }


}

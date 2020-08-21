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
    private String shop_name;
    private String shop_id;
    private String shop_img;
    private String goods_notic;
    private String all_money;
    private String address_num;
    private String last_time;
    private String pay_time;
    private String fa_huo_time;
    private String fa_huo_num;
    private String yun_fei;
    private String is_pj;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public String getIs_pj() {
        return is_pj;
    }

    public void setIs_pj(String is_pj) {
        this.is_pj = is_pj;
    }

    public String getYun_fei() {
        return yun_fei;
    }

    public void setYun_fei(String yun_fei) {
        this.yun_fei = yun_fei;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getFa_huo_time() {
        return fa_huo_time;
    }

    public void setFa_huo_time(String fa_huo_time) {
        this.fa_huo_time = fa_huo_time;
    }

    public String getFa_huo_num() {
        return fa_huo_num;
    }

    public void setFa_huo_num(String fa_huo_num) {
        this.fa_huo_num = fa_huo_num;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

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

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String goods_id) {
        this.shop_name = goods_id;
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

    @Override
    public String toString() {
        return "OrderSys{" +
                "id=" + id +
                ", creat_time='" + creat_time + '\'' +
                ", user_id='" + user_id + '\'' +
                ", pay_status='" + pay_status + '\'' +
                ", order_num='" + order_num + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", goods_notic='" + goods_notic + '\'' +
                ", all_money='" + all_money + '\'' +
                ", address_num='" + address_num + '\'' +
                ", last_time='" + last_time + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", fa_huo_time='" + fa_huo_time + '\'' +
                ", fa_huo_num='" + fa_huo_num + '\'' +
                '}';
    }

    public OrderSys() {
    }


}

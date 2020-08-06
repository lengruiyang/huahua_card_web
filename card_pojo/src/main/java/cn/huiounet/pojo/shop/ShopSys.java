package cn.huiounet.pojo.shop;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_shop")
public class ShopSys implements Serializable{
    private int id;
    private String shop_name;
    private String shop_head_img;
    private String shop_status;//商城状态
    private String bindtap;//点击量
    private String fire_num; //热度
    private String fans;
    private String user_open_id;//管理者

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_head_img() {
        return shop_head_img;
    }

    public void setShop_head_img(String shop_head_img) {
        this.shop_head_img = shop_head_img;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getBindtap() {
        return bindtap;
    }

    public void setBindtap(String bindtap) {
        this.bindtap = bindtap;
    }

    public String getFire_num() {
        return fire_num;
    }

    public void setFire_num(String fire_num) {
        this.fire_num = fire_num;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getUser_open_id() {
        return user_open_id;
    }

    public void setUser_open_id(String user_open_id) {
        this.user_open_id = user_open_id;
    }

    @Override
    public String toString() {
        return "ShopSys{" +
                "id=" + id +
                ", shop_name='" + shop_name + '\'' +
                ", shop_head_img='" + shop_head_img + '\'' +
                ", shop_status='" + shop_status + '\'' +
                ", bindtap='" + bindtap + '\'' +
                ", fire_num='" + fire_num + '\'' +
                ", fans='" + fans + '\'' +
                ", user_open_id='" + user_open_id + '\'' +
                '}';
    }

    public ShopSys() {
    }
}

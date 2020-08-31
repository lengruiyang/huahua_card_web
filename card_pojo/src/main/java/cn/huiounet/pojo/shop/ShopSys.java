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
    private String user_pj;
    private String wu_liu;
    private String shou_hou;
    private String id_card;
    private String shop_qr_code;
    private String shop_mess;
    private String location;
    private String create_time;
    private String sell_goods;

    public String getUser_pj() {
        return user_pj;
    }

    public void setUser_pj(String user_pj) {
        this.user_pj = user_pj;
    }

    public String getWu_liu() {
        return wu_liu;
    }

    public void setWu_liu(String wu_liu) {
        this.wu_liu = wu_liu;
    }

    public String getShou_hou() {
        return shou_hou;
    }

    public void setShou_hou(String shou_hou) {
        this.shou_hou = shou_hou;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getShop_qr_code() {
        return shop_qr_code;
    }

    public void setShop_qr_code(String shop_qr_code) {
        this.shop_qr_code = shop_qr_code;
    }

    public String getShop_mess() {
        return shop_mess;
    }

    public void setShop_mess(String shop_mess) {
        this.shop_mess = shop_mess;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSell_goods() {
        return sell_goods;
    }

    public void setSell_goods(String sell_goods) {
        this.sell_goods = sell_goods;
    }

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


    public ShopSys() {
    }
}

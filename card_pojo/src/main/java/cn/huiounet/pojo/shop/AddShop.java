package cn.huiounet.pojo.shop;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/17 16:09
 * @Version 1.0
 **/
@Table(name = "card_shop_add")
public class AddShop implements Serializable {
    private int id;
    private String name;
    private String shop_img;
    private String xian_xia;
    private String latitude;
    private String longitude;
    private String user_open_id;
    private String shop_mess;
    private String sell_goods;
    private String add_time;
    private String id_card_img_url;
    private String user_name;
    private String shop_zhi_zhao; //营业执照
    private String status; //0:审核 1：通过 2：未通过
    private String status_why;//未通过原因
    private String user_id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "AddShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shop_img='" + shop_img + '\'' +
                ", xian_xia='" + xian_xia + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", user_open_id='" + user_open_id + '\'' +
                ", shop_mess='" + shop_mess + '\'' +
                ", sell_goods='" + sell_goods + '\'' +
                ", id_card_img_url='" + id_card_img_url + '\'' +
                ", shop_zhi_zhao='" + shop_zhi_zhao + '\'' +
                ", status='" + status + '\'' +
                ", status_why='" + status_why + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public String getXian_xia() {
        return xian_xia;
    }

    public void setXian_xia(String xian_xia) {
        this.xian_xia = xian_xia;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUser_open_id() {
        return user_open_id;
    }

    public void setUser_open_id(String user_open_id) {
        this.user_open_id = user_open_id;
    }

    public String getShop_mess() {
        return shop_mess;
    }

    public void setShop_mess(String shop_mess) {
        this.shop_mess = shop_mess;
    }

    public String getSell_goods() {
        return sell_goods;
    }

    public void setSell_goods(String sell_goods) {
        this.sell_goods = sell_goods;
    }

    public String getId_card_img_url() {
        return id_card_img_url;
    }

    public void setId_card_img_url(String id_card_img_url) {
        this.id_card_img_url = id_card_img_url;
    }

    public String getShop_zhi_zhao() {
        return shop_zhi_zhao;
    }

    public void setShop_zhi_zhao(String shop_zhi_zhao) {
        this.shop_zhi_zhao = shop_zhi_zhao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_why() {
        return status_why;
    }

    public void setStatus_why(String status_why) {
        this.status_why = status_why;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public AddShop() {
    }
}

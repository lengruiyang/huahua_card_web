package cn.huiounet.pojo.coupon;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 满减券
 */
@Table(name = "card_coupon_sys")
public class CouponSys implements Serializable{
    private String id;
    private String name;
    private String price; //减去多少元
    private String enough_price;//达到价格
    private String about_mess;
    private String status; //0未领取，1：领取，未使用，2：使用了，3：过期
    private String category_tip;//分类标签
    private String category;//分类
    private String create_time; //系统创建时间
    private String start_time; //领取时间
    private String last_time;
    private String long_time;
    private String user_id;
    private String where_from; //sys:系统  goods:商品 shop:商店

    public String getWhere_from() {
        return where_from;
    }

    public void setWhere_from(String where_from) {
        this.where_from = where_from;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public CouponSys() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEnough_price() {
        return enough_price;
    }

    public void setEnough_price(String enough_price) {
        this.enough_price = enough_price;
    }

    public String getAbout_mess() {
        return about_mess;
    }

    public void setAbout_mess(String about_mess) {
        this.about_mess = about_mess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory_tip() {
        return category_tip;
    }

    public void setCategory_tip(String category_tip) {
        this.category_tip = category_tip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getLong_time() {
        return long_time;
    }

    public void setLong_time(String long_time) {
        this.long_time = long_time;
    }
}

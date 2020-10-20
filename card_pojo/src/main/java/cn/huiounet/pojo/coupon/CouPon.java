package cn.huiounet.pojo.coupon;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_coupon")
public class CouPon implements Serializable{
    private int id;
    private String name;
    private String status;
    private String user_status; //1.所有人可用 2:vip可用 3：新人
    private String coupon_status;//1:全场2：指定商品3：指定店铺；
    private String price; //减去多少元
    private String enough_price;//达到价格
    private String about_mess;
    private String category_tip;//分类标签
    private String category;//分类
    private String create_time; //系统创建时间
    private String last_time; //从什么时候开始
    private String long_time;
    private String shop_id;
    private String goods_id;
    private String is_num;
    private String num;

    public String getIs_num() {
        return is_num;
    }

    @Override
    public String toString() {
        return "CouPon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", user_status='" + user_status + '\'' +
                ", coupon_status='" + coupon_status + '\'' +
                ", price='" + price + '\'' +
                ", enough_price='" + enough_price + '\'' +
                ", about_mess='" + about_mess + '\'' +
                ", category_tip='" + category_tip + '\'' +
                ", category='" + category + '\'' +
                ", create_time='" + create_time + '\'' +
                ", last_time='" + last_time + '\'' +
                ", long_time='" + long_time + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", is_num='" + is_num + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public void setIs_num(String is_num) {
        this.is_num = is_num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getCoupon_status() {
        return coupon_status;
    }

    public void setCoupon_status(String coupon_status) {
        this.coupon_status = coupon_status;
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


    public CouPon() {
    }
}

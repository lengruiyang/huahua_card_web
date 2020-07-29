package cn.huiounet.pojo.cart;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_cart_sys")
public class CartSys  implements Serializable{
    private int id;
    private String goods_id;
    private String goods_name;
    private String goods_num;
    private String goods_price;
    private String create_time;
    private String color;
    private String size;
    private String user_id;
    private String img;
    private String about_mess; //相关信息
    private String status_;

    public String getStatus() {
        return status_;
    }

    public void setStatus(String status) {
        this.status_ = status;
    }

    @Override
    public String toString() {
        return "CartSys{" +
                "id=" + id +
                ", goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_num='" + goods_num + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", create_time='" + create_time + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", user_id='" + user_id + '\'' +
                ", img='" + img + '\'' +
                ", about_mess='" + about_mess + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAbout_mess() {
        return about_mess;
    }

    public void setAbout_mess(String about_mess) {
        this.about_mess = about_mess;
    }

    public CartSys() {
    }
}

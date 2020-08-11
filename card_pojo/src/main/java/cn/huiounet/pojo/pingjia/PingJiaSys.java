package cn.huiounet.pojo.pingjia;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_pingjia")
public class PingJiaSys implements Serializable {
    private int id;
    private String goods_id;
    private String star;
    private String pingjia;
    private String img_one;
    private String img_two;
    private String user_img;
    private String user_name;
    private String user_id;
    private String time;
    private String order_num;
    private String color;
    private String szie;

    public String getSzie() {
        return szie;
    }

    public void setSzie(String szie) {
        this.szie = szie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    @Override
    public String toString() {
        return "PingJiaSys{" +
                "id=" + id +
                ", goods_id='" + goods_id + '\'' +
                ", star='" + star + '\'' +
                ", pingjia='" + pingjia + '\'' +
                ", img_one='" + img_one + '\'' +
                ", img_two='" + img_two + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", time='" + time + '\'' +
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPingjia() {
        return pingjia;
    }

    public void setPingjia(String pingjia) {
        this.pingjia = pingjia;
    }

    public String getImg_one() {
        return img_one;
    }

    public void setImg_one(String img_one) {
        this.img_one = img_one;
    }

    public String getImg_two() {
        return img_two;
    }

    public void setImg_two(String img_two) {
        this.img_two = img_two;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PingJiaSys() {
    }
}

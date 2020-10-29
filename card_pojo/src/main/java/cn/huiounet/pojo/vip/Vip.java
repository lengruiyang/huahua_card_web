package cn.huiounet.pojo.vip;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_vip")
public class Vip implements Serializable {
    private int id;
    private String price;
    private String name;
    private String time;
    private String is_give_coupon;
    private String coupon_id;
    private String is_add_jifen;
    private String add_jifen_much;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_give_coupon() {
        return is_give_coupon;
    }

    public void setIs_give_coupon(String is_give_coupon) {
        this.is_give_coupon = is_give_coupon;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getIs_add_jifen() {
        return is_add_jifen;
    }

    public void setIs_add_jifen(String is_add_jifen) {
        this.is_add_jifen = is_add_jifen;
    }

    public String getAdd_jifen_much() {
        return add_jifen_much;
    }

    public void setAdd_jifen_much(String add_jifen_much) {
        this.add_jifen_much = add_jifen_much;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Vip() {
    }

    public Vip(int id, String price, String time) {
        this.id = id;
        this.price = price;
        this.time = time;
    }
}

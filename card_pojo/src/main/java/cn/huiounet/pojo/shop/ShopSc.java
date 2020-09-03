package cn.huiounet.pojo.shop;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_shop_sc")
public class ShopSc implements Serializable{
    private int id;
    private String shop_id;
    private String user_id;
    private String create_time;

    public ShopSc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

package cn.huiounet.pojo.shop;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_shop_vip")
public class ShopVip implements Serializable {
    private int id;
    private String name;
    private String shop_id;

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

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public ShopVip() {
    }
}

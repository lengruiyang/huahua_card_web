package cn.huiounet.pojo.diandan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_order_diandan_fenlei")
public class OrderDianDanFenLei implements Serializable {
    private int id;
    private String shop_id;
    private String fenlei;

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

    public String getFenlei() {
        return fenlei;
    }

    public void setFenlei(String fenlei) {
        this.fenlei = fenlei;
    }

    public OrderDianDanFenLei() {
    }

    public OrderDianDanFenLei(int id, String shop_id, String fenlei) {
        this.id = id;
        this.shop_id = shop_id;
        this.fenlei = fenlei;
    }
}

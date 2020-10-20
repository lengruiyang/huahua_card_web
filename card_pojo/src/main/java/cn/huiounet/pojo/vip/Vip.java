package cn.huiounet.pojo.vip;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_vip")
public class Vip implements Serializable {
    private int id;
    private String price;
    private String time;

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

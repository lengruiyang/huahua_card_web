package cn.huiounet.pojo.goods;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_goods_sc")
public class GoodsSc implements Serializable{
    private int id;
    private String goods_id;
    private String user_id;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public GoodsSc() {
    }

    public GoodsSc(int id, String goods_id, String user_id) {
        this.id = id;
        this.goods_id = goods_id;
        this.user_id = user_id;
    }
}

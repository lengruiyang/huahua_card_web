package cn.huiounet.pojo.root;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_goods_root")
public class GoodsRoot implements Serializable {
    private int id;
    private String user_id;
    private String create_time;
    private String goods_id;
    private String goods_img;
    private String goods_name;
    private String price;

    @Override
    public String toString() {
        return "GoodsRoot{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_img='" + goods_img + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public GoodsRoot() {
    }
}

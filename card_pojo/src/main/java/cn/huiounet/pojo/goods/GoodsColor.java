package cn.huiounet.pojo.goods;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_goods_color")
public class GoodsColor implements Serializable{
    private int id;
    private String status;
    private String color;
    private String img;
    private String goods_id;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public GoodsColor() {
    }

    public GoodsColor(int id, String status, String color) {
        this.id = id;
        this.status = status;
        this.color = color;
    }
}

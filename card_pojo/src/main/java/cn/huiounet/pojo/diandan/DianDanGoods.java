package cn.huiounet.pojo.diandan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_diandan_goods")
public class DianDanGoods implements Serializable {
    private int id;
    private String goods_name;
    private String fen_lei_id;
    private String img;
    private String heng_img;
    private String status;
    private String is_new;
    private String price;
    private String price_num;

    public String getPrice_num() {
        return price_num;
    }

    public void setPrice_num(String price_num) {
        this.price_num = price_num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFen_lei_id() {
        return fen_lei_id;
    }

    public void setFen_lei_id(String fen_lei_id) {
        this.fen_lei_id = fen_lei_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHeng_img() {
        return heng_img;
    }

    public void setHeng_img(String heng_img) {
        this.heng_img = heng_img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public DianDanGoods() {
    }
}

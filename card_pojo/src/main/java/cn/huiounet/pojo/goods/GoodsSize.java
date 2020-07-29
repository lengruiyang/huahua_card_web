package cn.huiounet.pojo.goods;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_goods_size")
public class GoodsSize implements Serializable{
    private int id;
    private String size;
    private String company; //单位
    private String color_id;
    private String status_;

    public String getStatus() {
        return status_;
    }

    public void setStatus(String status) {
        this.status_ = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;
    public String getColor_id() {
        return color_id;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }

    public GoodsSize() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

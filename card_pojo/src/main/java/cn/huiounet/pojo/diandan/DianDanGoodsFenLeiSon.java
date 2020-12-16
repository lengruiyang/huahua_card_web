package cn.huiounet.pojo.diandan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_diandan_goods_fenlei_son")
public class DianDanGoodsFenLeiSon implements Serializable {
    private int id;
    private String mess;
    private String price;
    private String farther_id;

    public String getFarther_id() {
        return farther_id;
    }

    public void setFarther_id(String farther_id) {
        this.farther_id = farther_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DianDanGoodsFenLeiSon() {
    }
}

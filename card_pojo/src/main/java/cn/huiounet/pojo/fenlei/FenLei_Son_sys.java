package cn.huiounet.pojo.fenlei;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_fen_lei_son_sys")
public class FenLei_Son_sys implements Serializable {
    private int id;
    private String name;
    private String farther_id;
    private String img;

    public int getId() {
        return id;
    }

    public String getFarther_id() {
        return farther_id;
    }

    public void setFarther_id(String farther_id) {
        this.farther_id = farther_id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public FenLei_Son_sys() {
    }
}

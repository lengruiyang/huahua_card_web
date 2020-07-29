package cn.huiounet.pojo.fenlei;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_fen_lei_farther_sys")
public class FenLei_Farther_sys implements Serializable {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "FenLei_Farther_sys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public FenLei_Farther_sys() {
    }
}

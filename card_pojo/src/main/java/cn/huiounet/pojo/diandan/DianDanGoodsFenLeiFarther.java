package cn.huiounet.pojo.diandan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_diandan_goods_fenlei_farther")
public class DianDanGoodsFenLeiFarther implements Serializable {
    private int id;
    private String name;
    private String goods_id;

    public int getId() {
        return id;
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

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public DianDanGoodsFenLeiFarther() {
    }

    public DianDanGoodsFenLeiFarther(int id, String name, String goods_id) {
        this.id = id;
        this.name = name;
        this.goods_id = goods_id;
    }
}

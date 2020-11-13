package cn.huiounet.pojo.renwujifen;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_jifen_goods_fenlei")
public class JiFenGoodsFenLeiSys implements Serializable {
    private int id;
    private String name;
    private String status;

    public JiFenGoodsFenLeiSys() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
}

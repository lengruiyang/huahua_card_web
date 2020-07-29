package cn.huiounet.pojo.address;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_address_tip")
public class AddressTip implements Serializable {
    private String id;
    private String name;

    public AddressTip() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressTip(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

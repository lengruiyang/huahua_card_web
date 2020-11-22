package cn.huiounet.pojo.vo;

import java.io.Serializable;

public class BinUser implements Serializable {
    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BinUser(int value, String name) {
        this.value = value;
        this.name = name;
    }
}

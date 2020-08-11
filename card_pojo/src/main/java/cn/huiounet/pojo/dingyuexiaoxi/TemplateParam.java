package cn.huiounet.pojo.dingyuexiaoxi;

import java.io.Serializable;

/**
 * @author yd
 * @version 1.0
 * @date 2020/1/11 13:56
 */
public class TemplateParam implements Serializable {

    private String key;
    private String value;

    public TemplateParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TemplateParam{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}


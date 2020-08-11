package cn.huiounet.pojo.order;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_order_address")
public class OrderAddress implements Serializable {
    private int id;
    private String user_name;
    private String user_phone;
    private String user_address;
    private String order_num;
    private String tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public OrderAddress() {
    }

    public OrderAddress(int id, String user_name, String user_phone, String user_address, String order_num, String tip) {
        this.id = id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.order_num = order_num;
        this.tip = tip;
    }
}

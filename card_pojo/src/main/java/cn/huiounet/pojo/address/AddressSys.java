package cn.huiounet.pojo.address;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_address")
public class AddressSys implements Serializable{
    private int id;
    private String user_id;
    private String user_name;
    private String user_phone;
    private String user_address;
    private String tip; //标签
    private String create_time;
    private String status; //1，默认，0，不默认

    public AddressSys() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressSys(int id, String user_id, String user_name, String user_phone, String user_address, String tip, String create_time, String status) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.tip = tip;
        this.create_time = create_time;
        this.status = status;
    }
}

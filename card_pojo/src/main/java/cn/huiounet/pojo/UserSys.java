package cn.huiounet.pojo;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_sys_user")
public class UserSys implements Serializable {
    private int id;
    private String user_name;
    private String password;
    private String open_id;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSys() {
    }
}

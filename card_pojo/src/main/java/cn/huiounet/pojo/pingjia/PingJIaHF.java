package cn.huiounet.pojo.pingjia;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_pingjia_hf")
public class PingJIaHF implements Serializable {
    private int id;
    private String pj_id;
    private String mess;
    private String user_name;
    private String user_img;
    private String status;
    private String create_time;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public PingJIaHF() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PingJIaHF{" +
                "id=" + id +
                ", pj_id='" + pj_id + '\'' +
                ", mess='" + mess + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_img='" + user_img + '\'' +
                ", status='" + status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPjId() {
        return pj_id;
    }

    public void setPjId(String pj_id) {
        this.pj_id = pj_id;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

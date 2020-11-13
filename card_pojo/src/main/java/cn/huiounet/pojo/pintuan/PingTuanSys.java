package cn.huiounet.pojo.pintuan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_pingtuan_sys")
public class PingTuanSys implements Serializable{
    private int id;
    private String perpons;
    private String time;
    private String status;

    public PingTuanSys() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerpons() {
        return perpons;
    }

    public void setPerpons(String perpons) {
        this.perpons = perpons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

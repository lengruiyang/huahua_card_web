package cn.huiounet.pojo.daohang;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_dao_hang_sys")
public class DaoHangSys implements Serializable {
    private int id;
    private String name;
    private String img;
    private String to_url;
    private String status; //禁用
    private String whats_play; //功能
    private String create_time;

    @Override
    public String toString() {
        return "DaoHangSys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", to_url='" + to_url + '\'' +
                ", status='" + status + '\'' +
                ", whats_play='" + whats_play + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }

    public DaoHangSys() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTo_url() {
        return to_url;
    }

    public void setTo_url(String to_url) {
        this.to_url = to_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWhats_play() {
        return whats_play;
    }

    public void setWhats_play(String whats_play) {
        this.whats_play = whats_play;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

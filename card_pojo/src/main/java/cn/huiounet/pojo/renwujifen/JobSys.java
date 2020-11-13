package cn.huiounet.pojo.renwujifen;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_job_sys")
public class JobSys implements Serializable {
    private int id;
    private String name;
    private String add_jifen;
    private String is_vip;
    private String status;
    private String img_url;
    private String btn_text;
    private String to_url;

    public String getBtn_text() {
        return btn_text;
    }

    public void setBtn_text(String btn_text) {
        this.btn_text = btn_text;
    }

    public String getTo_url() {
        return to_url;
    }

    public void setTo_url(String to_url) {
        this.to_url = to_url;
    }

    public JobSys() {
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

    public String getAdd_jifen() {
        return add_jifen;
    }

    public void setAdd_jifen(String add_jifen) {
        this.add_jifen = add_jifen;
    }

    public String getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

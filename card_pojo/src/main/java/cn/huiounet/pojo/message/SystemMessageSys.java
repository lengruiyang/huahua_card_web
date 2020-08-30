package cn.huiounet.pojo.message;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_system_message")
public class SystemMessageSys implements Serializable{
    private int id;
    private String status;
    private String create_time;
    private String mesage;
    private String img;
    private String to_do;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }


    public SystemMessageSys() {
    }
}

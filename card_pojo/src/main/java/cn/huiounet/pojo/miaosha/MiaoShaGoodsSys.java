package cn.huiounet.pojo.miaosha;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 24小时等于86400000毫秒
 * 2小时7200000毫秒
 */
@Table(name = "card_miao_sha")
public class MiaoShaGoodsSys implements Serializable {
    private String id;
    private String name;
    private String start_time;
    private String status;
    private String long_time;

    public MiaoShaGoodsSys() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLong_time() {
        return long_time;
    }

    public void setLong_time(String long_time) {
        this.long_time = long_time;
    }

    public MiaoShaGoodsSys(String id, String name, String start_time, String status, String long_time) {
        this.id = id;
        this.name = name;
        this.start_time = start_time;
        this.status = status;
        this.long_time = long_time;
    }
}

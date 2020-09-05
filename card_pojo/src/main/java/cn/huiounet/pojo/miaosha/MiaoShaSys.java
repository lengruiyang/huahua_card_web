package cn.huiounet.pojo.miaosha;

import java.io.Serializable;

public class MiaoShaSys implements Serializable {
    private String name;
    private String status;
    private Long time; //还剩多久结束
    private Long start_time; //还剩多久开始

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MiaoShaSys{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", start_time='" + start_time + '\'' +
                '}';
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public MiaoShaSys() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public MiaoShaSys(String name, String status, Long time) {
        this.name = name;
        this.status = status;
        this.time = time;
    }
}

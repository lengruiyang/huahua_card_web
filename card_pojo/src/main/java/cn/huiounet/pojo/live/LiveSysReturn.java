package cn.huiounet.pojo.live;

import java.io.Serializable;

public class LiveSysReturn implements Serializable {
    private String anchor_name;
    private String cover_img;
    private String end_time;
    private String live_status;
    private String name;
    private String roomid;
    private String share_img;
    private String start_time;

    public LiveSysReturn() {
    }

    @Override
    public String toString() {
        return "LiveSysReturn{" +
                "anchor_name='" + anchor_name + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", end_time='" + end_time + '\'' +
                ", live_status='" + live_status + '\'' +
                ", name='" + name + '\'' +
                ", roomid='" + roomid + '\'' +
                ", share_img='" + share_img + '\'' +
                ", start_time='" + start_time + '\'' +
                '}';
    }

    public String getAnchor_name() {
        return anchor_name;
    }

    public void setAnchor_name(String anchor_name) {
        this.anchor_name = anchor_name;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLive_status() {
        return live_status;
    }

    public void setLive_status(String live_status) {
        this.live_status = live_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}

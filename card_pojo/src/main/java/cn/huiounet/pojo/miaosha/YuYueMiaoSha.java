package cn.huiounet.pojo.miaosha;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_yuyue")
public class YuYueMiaoSha implements Serializable {
    private int id;
    private String goods_id;
    private String user_id;
    private String start_time;

    public YuYueMiaoSha() {
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}

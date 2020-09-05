package cn.huiounet.pojo.video;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_video_sys")
public class VideoCardSys implements Serializable {
    private int id;
    private String url;
    private String goods_id;
    private String status;

    @Override
    public String toString() {
        return "VideoCardSys{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VideoCardSys() {
    }
}

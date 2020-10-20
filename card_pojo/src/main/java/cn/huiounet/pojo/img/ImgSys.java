package cn.huiounet.pojo.img;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_img_sys")
public class ImgSys implements Serializable {
    private int id;
    private String status; //2轮播图？1商品图 3:分类轮播 4首页
    private String to_url; //轮播图
    private String goods_id;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImgSys{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", to_url='" + to_url + '\'' +
                ", goods_id='" + goods_id + '\'' +
                '}';
    }

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

    public String getTo_url() {
        return to_url;
    }

    public void setTo_url(String to_url) {
        this.to_url = to_url;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public ImgSys(int id, String status, String to_url, String goods_id) {
        this.id = id;
        this.status = status;
        this.to_url = to_url;
        this.goods_id = goods_id;
    }

    public ImgSys() {
    }
}

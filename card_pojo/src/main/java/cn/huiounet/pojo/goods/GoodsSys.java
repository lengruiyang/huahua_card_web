package cn.huiounet.pojo.goods;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_goods_sys")
public class GoodsSys implements Serializable {
    private int id;
    private String goods_name;
    private String goods_cen_img; //主图
    private String second_name; //副标题
    private String tip; //标签
    private String sell_many;
    private String like_many;
    private String where_from;
    private String sc_price;
    private String low_price;
    private String hight_price;
    private String status; //0下架 1上架
    private String is_sport_vip;
    private String vip_price;
    private String get_score;
    private String shop_id;
    private String yun_fei;
    private String miaosha_id;
    private String is_miaosha;
    private String kucun;

    public String getKucun() {
        return kucun;
    }

    public void setKucun(String kucun) {
        this.kucun = kucun;
    }

    public String getMiaosha_id() {
        return miaosha_id;
    }

    public void setMiaosha_id(String miaosha_id) {
        this.miaosha_id = miaosha_id;
    }

    public String getIs_miaosha() {
        return is_miaosha;
    }

    public void setIs_miaosha(String is_miaosha) {
        this.is_miaosha = is_miaosha;
    }

    public String getYun_fei() {
        return yun_fei;
    }

    public void setYun_fei(String yun_fei) {
        this.yun_fei = yun_fei;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public GoodsSys() {
    }

    @Override

    public String toString() {
        return "GoodsSys{" +
                "id=" + id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_cen_img='" + goods_cen_img + '\'' +
                ", second_name='" + second_name + '\'' +
                ", tip='" + tip + '\'' +
                ", sell_many='" + sell_many + '\'' +
                ", like_many='" + like_many + '\'' +
                ", where_from='" + where_from + '\'' +
                ", sc_price='" + sc_price + '\'' +
                ", low_price='" + low_price + '\'' +
                ", hight_price='" + hight_price + '\'' +
                ", status='" + status + '\'' +
                ", is_sport_vip='" + is_sport_vip + '\'' +
                ", vip_price='" + vip_price + '\'' +
                ", get_score='" + get_score + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_cen_img() {
        return goods_cen_img;
    }

    public void setGoods_cen_img(String goods_cen_img) {
        this.goods_cen_img = goods_cen_img;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSell_many() {
        return sell_many;
    }

    public void setSell_many(String sell_many) {
        this.sell_many = sell_many;
    }

    public String getLike_many() {
        return like_many;
    }

    public void setLike_many(String like_many) {
        this.like_many = like_many;
    }

    public String getWhere_from() {
        return where_from;
    }

    public void setWhere_from(String where_from) {
        this.where_from = where_from;
    }

    public String getSc_price() {
        return sc_price;
    }

    public void setSc_price(String sc_price) {
        this.sc_price = sc_price;
    }

    public String getLow_price() {
        return low_price;
    }

    public void setLow_price(String low_price) {
        this.low_price = low_price;
    }

    public String getHight_price() {
        return hight_price;
    }

    public void setHight_price(String hight_price) {
        this.hight_price = hight_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_sport_vip() {
        return is_sport_vip;
    }

    public void setIs_sport_vip(String is_sport_vip) {
        this.is_sport_vip = is_sport_vip;
    }

    public String getVip_price() {
        return vip_price;
    }

    public void setVip_price(String vip_price) {
        this.vip_price = vip_price;
    }

    public String getGet_score() {
        return get_score;
    }

    public void setGet_score(String get_score) {
        this.get_score = get_score;
    }

    public GoodsSys(int id, String goods_name, String goods_cen_img, String second_name, String tip, String sell_many, String like_many, String where_from, String sc_price, String low_price, String hight_price, String status, String is_sport_vip, String vip_price, String get_score) {
        this.id = id;
        this.goods_name = goods_name;
        this.goods_cen_img = goods_cen_img;
        this.second_name = second_name;
        this.tip = tip;
        this.sell_many = sell_many;
        this.like_many = like_many;
        this.where_from = where_from;
        this.sc_price = sc_price;
        this.low_price = low_price;
        this.hight_price = hight_price;
        this.status = status;
        this.is_sport_vip = is_sport_vip;
        this.vip_price = vip_price;
        this.get_score = get_score;
    }
}

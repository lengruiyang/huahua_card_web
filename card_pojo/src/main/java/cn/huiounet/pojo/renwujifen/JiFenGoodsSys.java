package cn.huiounet.pojo.renwujifen;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_jifen_goods")
public class JiFenGoodsSys implements Serializable {
    private int id;
    private String goods_name;
    private String goods_cen_img; //主图
    private String second_name; //副标题
    private String tip; //标签
    private String is_need_price;
    private String price_num;
    private String jifen;
    private String goods_mess;
    private String status;
    private String sc_price;
    private String jifen_fen_lei;

    public String getJifen_fen_lei() {
        return jifen_fen_lei;
    }

    public void setJifen_fen_lei(String jifen_fen_lei) {
        this.jifen_fen_lei = jifen_fen_lei;
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

    public String getIs_need_price() {
        return is_need_price;
    }

    public void setIs_need_price(String is_need_price) {
        this.is_need_price = is_need_price;
    }

    public String getPrice_num() {
        return price_num;
    }

    public void setPrice_num(String price_num) {
        this.price_num = price_num;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public String getGoods_mess() {
        return goods_mess;
    }

    public void setGoods_mess(String goods_mess) {
        this.goods_mess = goods_mess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSc_price() {
        return sc_price;
    }

    public void setSc_price(String sc_price) {
        this.sc_price = sc_price;
    }

    public JiFenGoodsSys() {
    }
}

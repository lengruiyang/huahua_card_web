package cn.huiounet.pojo.renwujifen;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_jifen_order")
public class JiFenOrder implements Serializable {
    private int id;
    private String user_id;
    private String order_num;
    private String is_change;
    private String create_time;
    private String change_time;
    private String goods_id;
    private String goods_name;
    private String img;
    private String num;
    private String jifen_num;
    private String is_need_money;
    private String money;
    private String address_id;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getIs_change() {
        return is_change;
    }

    public void setIs_change(String is_change) {
        this.is_change = is_change;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getChange_time() {
        return change_time;
    }

    public void setChange_time(String change_time) {
        this.change_time = change_time;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getJifen_num() {
        return jifen_num;
    }

    public void setJifen_num(String jifen_num) {
        this.jifen_num = jifen_num;
    }

    public String getIs_need_money() {
        return is_need_money;
    }

    public void setIs_need_money(String is_need_money) {
        this.is_need_money = is_need_money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public JiFenOrder() {
    }
}

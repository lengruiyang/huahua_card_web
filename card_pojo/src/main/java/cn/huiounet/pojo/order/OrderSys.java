package cn.huiounet.pojo.order;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_order_sys")
public class OrderSys implements Serializable{
    private int id;
    private String creat_time;
    private String user_id;
    private String pay_status;
    private String order_num; //订单号
    private String shop_name;
    private String shop_id;
    private String shop_img;
    private String goods_notic;
    private String all_money;
    private String address_num;
    private String last_time;
    private String pay_time;
    private String fa_huo_time;
    private String fa_huo_num;
    private String yun_fei;
    private String is_pj;
    private String fa_piao;
    private String pay_num;
    private String all_pay;
    private String pay_way;
    private String is_tk;
    private String tk_money;
    private String tk_time;
    private String youhui_much;
    private String youhuiquan_id;
    private String youhui_status;//1:优惠券，2;会员卡，0:无优惠
    private String order_lx; //1普通 2秒杀 3拼团
    private String is_zh;
    private String zh_order_num;

    public String getIs_zh() {
        return is_zh;
    }

    public void setIs_zh(String is_zh) {
        this.is_zh = is_zh;
    }

    public String getZh_order_num() {
        return zh_order_num;
    }

    public void setZh_order_num(String zh_order_num) {
        this.zh_order_num = zh_order_num;
    }

    public String getYouhuiquan_id() {
        return youhuiquan_id;
    }

    public void setYouhuiquan_id(String youhuiquan_id) {
        this.youhuiquan_id = youhuiquan_id;
    }

    public String getYouhui_much() {
        return youhui_much;
    }

    public void setYouhui_much(String youhui_much) {
        this.youhui_much = youhui_much;
    }

    public String getYouhui_status() {
        return youhui_status;
    }

    public void setYouhui_status(String youhui_status) {
        this.youhui_status = youhui_status;
    }

    public String getOrder_lx() {
        return order_lx;
    }

    public void setOrder_lx(String order_lx) {
        this.order_lx = order_lx;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getIs_tk() {
        return is_tk;
    }

    public void setIs_tk(String is_tk) {
        this.is_tk = is_tk;
    }

    public String getTk_money() {
        return tk_money;
    }

    public void setTk_money(String tk_money) {
        this.tk_money = tk_money;
    }

    public String getTk_time() {
        return tk_time;
    }

    public void setTk_time(String tk_time) {
        this.tk_time = tk_time;
    }

    public String getAll_pay() {
        return all_pay;
    }

    public void setAll_pay(String all_pay) {
        this.all_pay = all_pay;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

    public String getFa_piao() {
        return fa_piao;
    }

    public void setFa_piao(String fa_piao) {
        this.fa_piao = fa_piao;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public String getIs_pj() {
        return is_pj;
    }

    public void setIs_pj(String is_pj) {
        this.is_pj = is_pj;
    }

    public String getYun_fei() {
        return yun_fei;
    }

    public void setYun_fei(String yun_fei) {
        this.yun_fei = yun_fei;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getFa_huo_time() {
        return fa_huo_time;
    }

    public void setFa_huo_time(String fa_huo_time) {
        this.fa_huo_time = fa_huo_time;
    }

    public String getFa_huo_num() {
        return fa_huo_num;
    }

    public void setFa_huo_num(String fa_huo_num) {
        this.fa_huo_num = fa_huo_num;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String goods_id) {
        this.shop_name = goods_id;
    }

    public String getGoods_notic() {
        return goods_notic;
    }

    public void setGoods_notic(String goods_notic) {
        this.goods_notic = goods_notic;
    }

    public String getAll_money() {
        return all_money;
    }

    public void setAll_money(String all_money) {
        this.all_money = all_money;
    }

    public String getAddress_num() {
        return address_num;
    }

    public void setAddress_num(String address_num) {
        this.address_num = address_num;
    }

    @Override
    public String toString() {
        return "OrderSys{" +
                "id=" + id +
                ", creat_time='" + creat_time + '\'' +
                ", user_id='" + user_id + '\'' +
                ", pay_status='" + pay_status + '\'' +
                ", order_num='" + order_num + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", goods_notic='" + goods_notic + '\'' +
                ", all_money='" + all_money + '\'' +
                ", address_num='" + address_num + '\'' +
                ", last_time='" + last_time + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", fa_huo_time='" + fa_huo_time + '\'' +
                ", fa_huo_num='" + fa_huo_num + '\'' +
                '}';
    }

    public OrderSys() {
    }


}

package cn.huiounet.pojo.order;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_sell_after")
public class OrderSellAfter implements Serializable {
    private int id;
    private String why_tk;
    private String order_num;
    private String tk_money;
    private String say_mess; //说明
    private String create_time;
    private String status; //状态 1:通过 2：不通过
    private String user_id;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OrderSellAfter{" +
                "id=" + id +
                ", why_tk='" + why_tk + '\'' +
                ", order_num='" + order_num + '\'' +
                ", tk_money='" + tk_money + '\'' +
                ", say_mess='" + say_mess + '\'' +
                ", create_time='" + create_time + '\'' +
                ", status='" + status + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhy_tk() {
        return why_tk;
    }

    public void setWhy_tk(String why_tk) {
        this.why_tk = why_tk;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getTk_money() {
        return tk_money;
    }

    public void setTk_money(String tk_money) {
        this.tk_money = tk_money;
    }

    public String getSay_mess() {
        return say_mess;
    }

    public void setSay_mess(String say_mess) {
        this.say_mess = say_mess;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public OrderSellAfter() {
    }
}

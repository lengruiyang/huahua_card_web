package cn.huiounet.pojo.notic;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/26 11:07
 * @Version 1.0
 **/
@Table(name = "card_pay_log")
public class PayLogNoticPojo implements Serializable {
    private int id;
    private String user_id;
    private String status;
    private String order_num;
    private String create_time;
    private String pay_num;
    private String day_time;

    @Override
    public String toString() {
        return "PayLogNoticPojo{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", status='" + status + '\'' +
                ", order_num='" + order_num + '\'' +
                ", create_time='" + create_time + '\'' +
                ", pay_num='" + pay_num + '\'' +
                ", day_time='" + day_time + '\'' +
                '}';
    }

    public PayLogNoticPojo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getDay_time() {
        return day_time;
    }

    public void setDay_time(String day_time) {
        this.day_time = day_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

}

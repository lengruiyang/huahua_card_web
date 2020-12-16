package cn.huiounet.pojo.diandan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_order_diandan_shop")
public class OrderDianDanShop implements Serializable {
    private int id;
    private String name;
    private String create_time;
    private String qr_code;
    private String status;
    private String super_person;
    private String img_one;
    private String img_two;
    private String img_three;

    public String getImg_one() {
        return img_one;
    }

    public void setImg_one(String img_one) {
        this.img_one = img_one;
    }

    public String getImg_two() {
        return img_two;
    }

    public void setImg_two(String img_two) {
        this.img_two = img_two;
    }

    public String getImg_three() {
        return img_three;
    }

    public void setImg_three(String img_three) {
        this.img_three = img_three;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuper_person() {
        return super_person;
    }

    public void setSuper_person(String super_person) {
        this.super_person = super_person;
    }

    public OrderDianDanShop() {
    }
}

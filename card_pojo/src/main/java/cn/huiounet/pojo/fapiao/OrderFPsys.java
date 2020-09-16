package cn.huiounet.pojo.fapiao;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "order_fp_sys")
public class OrderFPsys implements Serializable{
    private int id;
    private String order_num;
    private String status;
    private String phone;
    private String email;
    private String user_name;
    private String company_name;
    private String company_code;
    private String company_address;
    private String company_phone;
    private String company_bank;
    private String bampany_card;
    private String user_id;
    private String create_time;
    private String fp_status;

    public String getFp_status() {
        return fp_status;
    }

    public void setFp_status(String fp_status) {
        this.fp_status = fp_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_bank() {
        return company_bank;
    }

    public void setCompany_bank(String company_bank) {
        this.company_bank = company_bank;
    }

    public String getBampany_card() {
        return bampany_card;
    }

    public void setBampany_card(String bampany_card) {
        this.bampany_card = bampany_card;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public OrderFPsys() {
    }

}

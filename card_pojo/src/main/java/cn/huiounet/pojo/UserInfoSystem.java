package cn.huiounet.pojo;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_user_info")
public class UserInfoSystem implements Serializable {
    private int id;
    private String head_img;
    private String nick_name;
    private String qian_ming;
    private String open_id;
    private String sex;
    private String birthday;
    private String phone_number;
    private String status;
    private String settting_num;
    private String create_time;

    @Override
    public String toString() {
        return "UserInfoSystem{" +
                "id=" + id +
                ", head_img='" + head_img + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", qian_ming='" + qian_ming + '\'' +
                ", open_id='" + open_id + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", status='" + status + '\'' +
                ", settting_num='" + settting_num + '\'' +
                ", create_time='" + create_time + '\'' +
                ", password='" + password + '\'' +
                ", user_tuijian_id='" + user_tuijian_id + '\'' +
                ", from_id='" + from_id + '\'' +
                '}';
    }

    private String password;
    private String user_tuijian_id;
    private String from_id;

    public String getUser_tuijian_id() {
        return user_tuijian_id;
    }

    public void setUser_tuijian_id(String user_tuijian_id) {
        this.user_tuijian_id = user_tuijian_id;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public UserInfoSystem() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getQian_ming() {
        return qian_ming;
    }

    public void setQian_ming(String qian_ming) {
        this.qian_ming = qian_ming;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSettting_num() {
        return settting_num;
    }

    public void setSettting_num(String settting_num) {
        this.settting_num = settting_num;
    }
}

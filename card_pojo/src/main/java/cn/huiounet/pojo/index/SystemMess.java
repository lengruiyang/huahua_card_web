package cn.huiounet.pojo.index;

import java.io.Serializable;

public class SystemMess implements Serializable {
    private Long price;
    private Long peopleNum;
    private Long orderNum;
    private Long messageNum;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Long peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Long messageNum) {
        this.messageNum = messageNum;
    }

    public SystemMess(Long price, Long peopleNum, Long orderNum, Long messageNum) {
        this.price = price;
        this.peopleNum = peopleNum;
        this.orderNum = orderNum;
        this.messageNum = messageNum;
    }
}

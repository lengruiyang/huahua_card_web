package cn.huiounet.pojo.vo;

import java.io.Serializable;

public class OrderResult implements Serializable {
    private String order_num;
    private Result result;

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public OrderResult(String order_num, Result result) {
        this.order_num = order_num;
        this.result = result;
    }
}

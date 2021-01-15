package cn.huiounet.pojo.notic;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/27 19:19
 * @Version 1.0
 **/
public class ReturnPayLog implements Serializable {
    private Float money;
    private String month;
    private List<PayLogNoticPojo> logNoticPojos;

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<PayLogNoticPojo> getLogNoticPojos() {
        return logNoticPojos;
    }

    public void setLogNoticPojos(List<PayLogNoticPojo> logNoticPojos) {
        this.logNoticPojos = logNoticPojos;
    }

    public ReturnPayLog() {
    }
}

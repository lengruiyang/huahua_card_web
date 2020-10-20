package cn.huiounet.pojo.order;

import java.util.Map;

public class ReturnCz {
    private Map<String,String> map;
    private String order_num;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public ReturnCz(Map<String, String> map, String order_num) {
        this.map = map;
        this.order_num = order_num;
    }
}

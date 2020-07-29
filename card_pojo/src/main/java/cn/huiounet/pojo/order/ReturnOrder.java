package cn.huiounet.pojo.order;

import java.io.Serializable;
import java.util.List;

public class ReturnOrder implements Serializable {
    private List<ReturnGoods> returnGoods;
    private OrderSys orderSys;

    public List<ReturnGoods> getReturnGoods() {
        return returnGoods;
    }

    public void setReturnGoods(List<ReturnGoods> returnGoods) {
        this.returnGoods = returnGoods;
    }

    public OrderSys getOrderSys() {
        return orderSys;
    }

    public void setOrderSys(OrderSys orderSys) {
        this.orderSys = orderSys;
    }

    public ReturnOrder(List<ReturnGoods> returnGoods, OrderSys orderSys) {
        this.returnGoods = returnGoods;
        this.orderSys = orderSys;
    }
}

package cn.huiounet.pojo.order;

import java.io.Serializable;
import java.util.List;

public class ReturnSellAfter implements Serializable {
    private OrderSys orderSys;
    private List<ReturnGoods> returnGoods;
    private OrderSellAfter orderSellAfter;

    public OrderSys getOrderSys() {
        return orderSys;
    }

    public void setOrderSys(OrderSys orderSys) {
        this.orderSys = orderSys;
    }

    public List<ReturnGoods> getReturnGoods() {
        return returnGoods;
    }

    public void setReturnGoods(List<ReturnGoods> returnGoods) {
        this.returnGoods = returnGoods;
    }

    public OrderSellAfter getOrderSellAfter() {
        return orderSellAfter;
    }

    public void setOrderSellAfter(OrderSellAfter orderSellAfter) {
        this.orderSellAfter = orderSellAfter;
    }

    public ReturnSellAfter(OrderSys orderSys, List<ReturnGoods> returnGoods, OrderSellAfter orderSellAfter) {
        this.orderSys = orderSys;
        this.returnGoods = returnGoods;
        this.orderSellAfter = orderSellAfter;
    }
}

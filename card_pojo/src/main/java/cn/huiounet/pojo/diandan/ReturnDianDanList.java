package cn.huiounet.pojo.diandan;

import java.util.List;

public class ReturnDianDanList {
    private OrderDianDanFenLei orderDianDanFenLei;
    private List<DianDanGoods> dianDanGoods;

    public OrderDianDanFenLei getOrderDianDanFenLei() {
        return orderDianDanFenLei;
    }

    public void setOrderDianDanFenLei(OrderDianDanFenLei orderDianDanFenLei) {
        this.orderDianDanFenLei = orderDianDanFenLei;
    }

    public List<DianDanGoods> getDianDanGoods() {
        return dianDanGoods;
    }

    public void setDianDanGoods(List<DianDanGoods> dianDanGoods) {
        this.dianDanGoods = dianDanGoods;
    }

    public ReturnDianDanList(OrderDianDanFenLei orderDianDanFenLei, List<DianDanGoods> dianDanGoods) {
        this.orderDianDanFenLei = orderDianDanFenLei;
        this.dianDanGoods = dianDanGoods;
    }
}

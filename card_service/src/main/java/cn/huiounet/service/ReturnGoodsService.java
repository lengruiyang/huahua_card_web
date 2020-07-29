package cn.huiounet.service;

import cn.huiounet.pojo.order.ReturnGoods;

import java.util.List;

public interface ReturnGoodsService {

    List<ReturnGoods> findByOrderNUm(String order_num);

    void saveReturnGoods(ReturnGoods returnGoods);
}

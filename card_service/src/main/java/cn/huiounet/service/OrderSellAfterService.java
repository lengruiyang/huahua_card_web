package cn.huiounet.service;

import cn.huiounet.pojo.order.OrderSellAfter;

import java.util.List;

public interface OrderSellAfterService {

    OrderSellAfter findByOrderNum(String order_num);

    List<OrderSellAfter> findByUserId(String user_id);

    void saveSellAfter(OrderSellAfter orderSellAfter);

    void updateStatus(String status,String result,String order_num);
}

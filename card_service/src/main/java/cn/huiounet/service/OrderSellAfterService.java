package cn.huiounet.service;

import cn.huiounet.pojo.order.OrderSellAfter;

import java.util.List;

public interface OrderSellAfterService {

    OrderSellAfter findByOrderNum(String order_num);

    List<OrderSellAfter> findByUserId(String user_id,int start,int size);

    int findByUserIdNum(String user_id);

    void saveSellAfter(OrderSellAfter orderSellAfter);

    void updateStatus(String status,String result,String work_time,String order_num);

    void updateUserThink(String user_think,String order_num);
}

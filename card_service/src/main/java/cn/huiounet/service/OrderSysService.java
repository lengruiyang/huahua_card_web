package cn.huiounet.service;

import cn.huiounet.pojo.order.OrderSys;

public interface OrderSysService {

    OrderSys findByOrderNum(String order_num);

    void saveOrder(OrderSys orderSys);
}

package cn.huiounet.service;

import cn.huiounet.pojo.order.OrderAddress;

public interface OrderAddressService {

    OrderAddress findByOrderNum(String order_num);

    void saveOrderAddress(OrderAddress orderAddress);

    void updateByOrderNum(String tip,String user_name,String user_phone,String user_address,String order_num);
}

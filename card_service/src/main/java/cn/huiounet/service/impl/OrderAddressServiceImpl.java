package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderAddressMapper;
import cn.huiounet.pojo.order.OrderAddress;
import cn.huiounet.service.OrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderAddressServiceImpl implements OrderAddressService {
    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Override
    public OrderAddress findByOrderNum(String order_num) {
        return orderAddressMapper.findByOrderNum(order_num);
    }

    @Override
    public void saveOrderAddress(OrderAddress orderAddress) {
        orderAddressMapper.insert(orderAddress);
    }

    @Override
    public void updateByOrderNum(String tip,String user_name, String user_phone, String user_address, String order_num) {
        orderAddressMapper.updateByOrderNum(tip,user_name, user_phone, user_address, order_num);
    }
}

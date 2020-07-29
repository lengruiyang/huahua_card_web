package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderSysMapper;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.service.OrderSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSysServiceImpl implements OrderSysService {
    @Autowired
    private OrderSysMapper orderSysMapper;

    @Override
    public OrderSys findByOrderNum(String order_num) {
        return orderSysMapper.findByOrderNum(order_num);
    }

    @Override
    public void saveOrder(OrderSys orderSys) {
        orderSysMapper.insert(orderSys);
    }
}

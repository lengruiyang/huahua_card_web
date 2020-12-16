package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderDianDanShopMapper;
import cn.huiounet.pojo.diandan.OrderDianDanShop;
import cn.huiounet.service.OrderDianDanShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDianDanShopServiceImpl implements OrderDianDanShopService {
    @Autowired
    private OrderDianDanShopMapper orderDianDanShopMapper;

    @Override
    public OrderDianDanShop findById(int id) {
        return orderDianDanShopMapper.findById(id);
    }
}

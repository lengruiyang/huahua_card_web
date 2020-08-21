package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderFPsysMapper;
import cn.huiounet.pojo.fapiao.OrderFPsys;
import cn.huiounet.service.OrderFPsysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFPsysServiceImpl implements OrderFPsysService {
    @Autowired
    private OrderFPsysMapper orderFPsysMapper;

    @Override
    public OrderFPsys findByOrderNum(String order_num) {
        return orderFPsysMapper.findByOrderNum(order_num);
    }

    @Override
    public void saveFP(OrderFPsys orderFPsys) {
        orderFPsysMapper.insert(orderFPsys);
    }

    @Override
    public List<OrderFPsys> findByUserId(String user_id) {
        return orderFPsysMapper.findByUserId(user_id);
    }
}

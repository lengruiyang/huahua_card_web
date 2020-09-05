package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderSellAfterMapper;
import cn.huiounet.pojo.order.OrderSellAfter;
import cn.huiounet.service.OrderSellAfterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSellAfterServiceImpl implements OrderSellAfterService {
    @Autowired
    private OrderSellAfterMapper orderSellAfterMapper;

    @Override
    public OrderSellAfter findByOrderNum(String order_num) {
        return orderSellAfterMapper.findByOrderNum(order_num);
    }

    @Override
    public List<OrderSellAfter> findByUserId(String user_id,int start,int size) {
        return orderSellAfterMapper.findByUserId(user_id,start,size);
    }

    @Override
    public int findByUserIdNum(String user_id) {
        return orderSellAfterMapper.findByUserIdNum(user_id);
    }

    @Override
    public void saveSellAfter(OrderSellAfter orderSellAfter) {
        orderSellAfterMapper.insert(orderSellAfter);
    }

    @Override
    public void updateStatus(String status,String result,String work_time, String order_num) {
        orderSellAfterMapper.updateStatus(status,result,work_time, order_num);
    }

    @Override
    public void updateUserThink(String user_think, String order_num) {
        orderSellAfterMapper.updateUserThink(user_think, order_num);
    }
}

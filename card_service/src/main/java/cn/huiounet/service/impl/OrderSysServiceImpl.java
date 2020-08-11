package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderSysMapper;
import cn.huiounet.pojo.order.OrderSys;
import cn.huiounet.service.OrderSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void updataPayStatusByOrderNum(String pay_status, String order_num) {
        orderSysMapper.updataPayStatusByOrderNum(pay_status, order_num);
    }

    @Override
    public void updateNotic(String goods_notic, String order_num) {
        orderSysMapper.updateNotic(goods_notic, order_num);
    }

    @Override
    public void updateAddress(String address_num, String order_num) {
        orderSysMapper.updateAddress(address_num, order_num);
    }

    @Override
    public void updataPayTime(String pay_time, String order_num) {
        orderSysMapper.updataPayTime(pay_time, order_num);
    }

    @Override
    public void updateFaHuo(String fa_huo_time, String fa_huo_num, String order_num) {
        orderSysMapper.updateFaHuo(fa_huo_time, fa_huo_num, order_num);
    }

    @Override
    public List<OrderSys> findOrderStatus(String user_id, String pay_status, int start, int size) {
        return orderSysMapper.findOrderStatus(user_id, pay_status, start, size);
    }

    @Override
    public List<OrderSys> findAll(String user_id,int start,int size) {
        return orderSysMapper.findAll(user_id,start,size);
    }

    @Override
    public int findAllNum(String user_id, String pay_status) {

        return orderSysMapper.findAllNum(user_id, pay_status);
    }


}

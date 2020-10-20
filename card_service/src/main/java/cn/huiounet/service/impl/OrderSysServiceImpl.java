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
    public void updateYouHui(String youhui_status, String youhui_much, String youhuiquan_id, String order_num) {
        orderSysMapper.updateYouHui(youhui_status, youhui_much, youhuiquan_id, order_num);
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
    public void updatePj(String is_pj, String order_num) {
        orderSysMapper.updatePj(is_pj, order_num);
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

    @Override
    public void updateFP(String fa_piao, String order_num) {
        orderSysMapper.updateFP(fa_piao, order_num);
    }

    @Override
    public void updatePayNum(String pay_num, String order_num) {
        orderSysMapper.updatePayNum(pay_num, order_num);
    }

    @Override
    public void updatePayNumById(String pay_num, String id) {
        orderSysMapper.updatePayNumById(pay_num, id);
    }

    @Override
    public List<OrderSys> findPayNumList(String pay_num) {
        return orderSysMapper.findPayNumList(pay_num);
    }

    @Override
    public void updateAll_pay(String all_pay, String order_num) {
        orderSysMapper.updateAll_pay(all_pay, order_num);
    }

    @Override
    public void deleteByOrderNum(String order_num) {
        orderSysMapper.deleteByOrderNum(order_num);
    }

    @Override
    public List<OrderSys> findOrderNumByUserId(String user_id) {
        return orderSysMapper.findOrderNumByUserId(user_id);
    }

    @Override
    public void updatePayWay(String pay_way, String order_num) {
        orderSysMapper.updatePayWay(pay_way, order_num);
    }

    @Override
    public void updateTk(String is_tk, String tk_money, String tk_time, String order_num) {
        orderSysMapper.updateTk(is_tk, tk_money, tk_time, order_num);
    }

    @Override
    public List<OrderSys> searchOrder(String user_id, String order_num, String shop_name, int start, int size) {
        return orderSysMapper.searchOrder(user_id, order_num, shop_name, start, size);
    }


}

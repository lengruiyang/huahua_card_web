package cn.huiounet.service.impl;

import cn.huiounet.dao.ZhuanZhangOrderMapper;
import cn.huiounet.pojo.order.ZhuanZhangOrder;
import cn.huiounet.service.ZhuanZhangOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZhuanZhangOrderServiceImpl implements ZhuanZhangOrderService {
    @Autowired
    private ZhuanZhangOrderMapper zhuanZhangOrderMapper;

    @Override
    public ZhuanZhangOrder findByOrderNum(String order_num) {
        return zhuanZhangOrderMapper.findByOrderNum(order_num);
    }

    @Override
    public void updateByOrderNum(String status, String order_num) {
        zhuanZhangOrderMapper.updateByOrderNum(status, order_num);
    }

    @Override
    public void saveOrder(ZhuanZhangOrder zhuanZhangOrder) {
        zhuanZhangOrderMapper.insert(zhuanZhangOrder);
    }
}

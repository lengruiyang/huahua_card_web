package cn.huiounet.service.impl;

import cn.huiounet.dao.CzOrderMapper;
import cn.huiounet.pojo.order.CzOrder;
import cn.huiounet.service.CzOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CzOrderServiceImpl implements CzOrderService {
    @Autowired
    private CzOrderMapper czOrderMapper;

    @Override
    public CzOrder findByUserId(String user_id) {
        return czOrderMapper.findByUserId(user_id);
    }

    @Override
    public CzOrder findByOrderNum(String order_num) {
        return czOrderMapper.findByOrderNum(order_num);
    }

    @Override
    public void updateStatus(String status, String order_num) {
        czOrderMapper.updateStatus(status, order_num);
    }

    @Override
    public void savePay(CzOrder czOrder) {
        czOrderMapper.insert(czOrder);
    }
}

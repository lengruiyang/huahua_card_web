package cn.huiounet.service.impl;

import cn.huiounet.dao.JiFenOrderMapper;
import cn.huiounet.pojo.renwujifen.JiFenOrder;
import cn.huiounet.service.JiFenOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiFenOrderServiceImpl implements JiFenOrderService {
    @Autowired
    private JiFenOrderMapper jiFenOrderMapper;
    @Override
    public JiFenOrder findByOrderNum(String order_num) {
        return jiFenOrderMapper.findByOrderNum(order_num);
    }

    @Override
    public void updateByOrderNum(String is_change, String change_time, String order_num) {
        jiFenOrderMapper.updateByOrderNum(is_change, change_time, order_num);
    }

    @Override
    public void saveOrder(JiFenOrder jiFenOrder) {
        jiFenOrderMapper.insert(jiFenOrder);
    }

    @Override
    public void updateAddressByOrderNum(String address_id, String order_num) {
        jiFenOrderMapper.updateAddressByOrderNum(address_id, order_num);
    }
}

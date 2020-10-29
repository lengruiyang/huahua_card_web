package cn.huiounet.service.impl;

import cn.huiounet.dao.HuaFeiOrderSysMapper;
import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import cn.huiounet.service.HuaFeiOrderSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuaFeiOrderSysServiceImpl implements HuaFeiOrderSysService {
    @Autowired
    private HuaFeiOrderSysMapper huaFeiOrderSysMapper;

    @Override
    public HuaFeiOrderSys findByOrderNum(String order_num) {
        return huaFeiOrderSysMapper.findByOrderNum(order_num);
    }

    @Override
    public void updateByOrderNum(String pay_status, String pay_time, String order_num) {
        huaFeiOrderSysMapper.updateByOrderNum(pay_status, pay_time, order_num);
    }

    @Override
    public void saveOrder(HuaFeiOrderSys huaFeiOrderSys) {
        huaFeiOrderSysMapper.insert(huaFeiOrderSys);
    }
}

package cn.huiounet.service.impl;

import cn.huiounet.dao.VipLogMapper;
import cn.huiounet.pojo.vip.VipLog;
import cn.huiounet.service.VipLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipLogServiceImpl implements VipLogService {
    @Autowired
    private VipLogMapper vipLogMapper;
    @Override
    public void updateByOrderNum(String status, String order_num) {
        vipLogMapper.updateByOrderNum(status, order_num);
    }

    @Override
    public void saveVipLog(VipLog vipLog) {
        vipLogMapper.insert(vipLog);
    }

    @Override
    public VipLog findByOrderNum(String order_num) {
        return vipLogMapper.findByOrderNum(order_num);
    }
}

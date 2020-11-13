package cn.huiounet.service.impl;

import cn.huiounet.dao.HuaFeiOrderSysMapper;
import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import cn.huiounet.service.HuaFeiOrderSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<HuaFeiOrderSys> findByUserId(String user_id, int start, int size) {
        return huaFeiOrderSysMapper.findByUserId(user_id, start, size);
    }

    @Override
    public List<HuaFeiOrderSys> findAll() {
        return huaFeiOrderSysMapper.selectAll();
    }

    @Override
    public List<HuaFeiOrderSys> findPayButNotSuccess(String is_cz_success) {
        return huaFeiOrderSysMapper.findPayButNotSuccess(is_cz_success);
    }

    @Override
    public void updateById(String is_cz_success, String id) {
        huaFeiOrderSysMapper.updateById(is_cz_success, id);
    }

    @Override
    public void deleteById(String id) {
        huaFeiOrderSysMapper.deleteById(id);
    }
}

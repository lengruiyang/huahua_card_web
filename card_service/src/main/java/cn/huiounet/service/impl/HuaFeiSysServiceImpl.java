package cn.huiounet.service.impl;

import cn.huiounet.dao.HuaFeiSysMapper;
import cn.huiounet.pojo.huafei.HuaFeiSys;
import cn.huiounet.service.HuaFeiSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuaFeiSysServiceImpl implements HuaFeiSysService {
    @Autowired
    private HuaFeiSysMapper huaFeiSysMapper;

    @Override
    public List<HuaFeiSys> findAll() {
        return huaFeiSysMapper.findAll();
    }

    @Override
    public HuaFeiSys findById(String id) {
        return huaFeiSysMapper.findById(id);
    }
}

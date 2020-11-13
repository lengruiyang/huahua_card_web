package cn.huiounet.service.impl;

import cn.huiounet.dao.PingTuanSysMapper;
import cn.huiounet.pojo.pintuan.PingTuanSys;
import cn.huiounet.service.PingTuanSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingTuanSysServiceImpl implements PingTuanSysService {
    @Autowired
    private PingTuanSysMapper pingTuanSysMapper;


    @Override
    public PingTuanSys findById(String id) {
        return pingTuanSysMapper.findById(id);
    }
}

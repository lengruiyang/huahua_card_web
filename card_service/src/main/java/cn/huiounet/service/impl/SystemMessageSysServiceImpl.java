package cn.huiounet.service.impl;

import cn.huiounet.dao.SystemMessageSysMapper;
import cn.huiounet.pojo.message.SystemMessageSys;
import cn.huiounet.service.SystemMessageSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemMessageSysServiceImpl implements SystemMessageSysService {
    @Autowired
    private SystemMessageSysMapper systemMessageSysMapper;

    @Override
    public List<SystemMessageSys> findByStatus() {
        return systemMessageSysMapper.findByStatus();
    }
}

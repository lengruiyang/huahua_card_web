package cn.huiounet.service.impl;

import cn.huiounet.dao.UserSysMapper;
import cn.huiounet.pojo.UserSys;
import cn.huiounet.service.UserSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSysServiceImpl implements UserSysService {
    @Autowired
    private UserSysMapper userSysMapper;
    @Override
    public UserSys findByUserName(String user_name) {
        return userSysMapper.findByUserName(user_name);
    }
}

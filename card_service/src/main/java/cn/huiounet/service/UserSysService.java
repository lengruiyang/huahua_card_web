package cn.huiounet.service;

import cn.huiounet.pojo.UserSys;

public interface UserSysService {

    UserSys findByUserName(String user_name);
}

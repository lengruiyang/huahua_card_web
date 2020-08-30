package cn.huiounet.service;

import cn.huiounet.pojo.message.SystemMessageSys;

import java.util.List;

public interface SystemMessageSysService {

    List<SystemMessageSys> findByStatus();
}

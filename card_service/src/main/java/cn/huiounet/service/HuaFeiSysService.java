package cn.huiounet.service;

import cn.huiounet.pojo.huafei.HuaFeiSys;

import java.util.List;

public interface HuaFeiSysService {

    List<HuaFeiSys> findAll();

    HuaFeiSys findById(String id);
}

package cn.huiounet.service;

import cn.huiounet.pojo.daohang.DaoHangSys;

import java.util.List;

public interface DaoHangSysService {

    List<DaoHangSys> findAll();

    List<DaoHangSys> findByStatus();

}

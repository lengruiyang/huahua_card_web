package cn.huiounet.service;

import cn.huiounet.pojo.daohang.DaoHangSys;

import java.util.List;

public interface DaoHangSysService {

    List<DaoHangSys> findAll();

    List<DaoHangSys> findByStatus();

    void save(DaoHangSys daoHangSys);

    void updateById(String status,int id);

    void updateMessById(String name,String img,String to_url,int id);

    void deleteById(int id);

    DaoHangSys findById(int id);

}

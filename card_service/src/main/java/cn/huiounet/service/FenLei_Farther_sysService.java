package cn.huiounet.service;

import cn.huiounet.pojo.fenlei.FenLei_Farther_sys;

import java.util.List;

public interface FenLei_Farther_sysService {

    FenLei_Farther_sys findById(String id);

    List<FenLei_Farther_sys> findAll();
}

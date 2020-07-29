package cn.huiounet.service;

import cn.huiounet.pojo.fenlei.FenLei_Son_sys;

import java.util.List;

public interface FenLei_Son_sysService {

    List<FenLei_Son_sys> findByFartherId(String farther_id);
}

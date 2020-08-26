package cn.huiounet.service;

import cn.huiounet.pojo.pingjia.PingJIaHF;

import java.util.List;

public interface PingJIaHFService {

    List<PingJIaHF> findByPjId(String pj_id,int start,int size);

    void savePjHf(PingJIaHF pingJIaHF);
}

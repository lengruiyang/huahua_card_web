package cn.huiounet.service;

import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;

import java.util.List;

public interface MiaoShaGoodsSysService {

    List<MiaoShaGoodsSys> findAll();

    void updateStartTime(String start_time,String id);

    MiaoShaGoodsSys findById(String id);
}

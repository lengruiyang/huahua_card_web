package cn.huiounet.service;

import cn.huiounet.pojo.renwujifen.JiFenGoodsSys;

import java.util.List;

public interface JiFenGoodsSysService {

    List<JiFenGoodsSys> findByFenLei(String jifen_fen_lei,int start,int size);

    JiFenGoodsSys findById(String id);
}

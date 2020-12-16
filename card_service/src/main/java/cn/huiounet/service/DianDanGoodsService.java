package cn.huiounet.service;

import cn.huiounet.pojo.diandan.DianDanGoods;

import java.util.List;

public interface DianDanGoodsService  {


    List<DianDanGoods> findByFenLeiId(String id);

    DianDanGoods findById(int id);
}

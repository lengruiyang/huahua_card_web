package cn.huiounet.dao;

import cn.huiounet.pojo.diandan.DianDanGoods;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DianDanGoodsMapper extends Mapper<DianDanGoods> {

    List<DianDanGoods> findByFenLeiId(String id);

    DianDanGoods findById(int id);

}

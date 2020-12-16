package cn.huiounet.dao;

import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiFarther;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DianDanGoodsFenLeiFartherMapper extends Mapper<DianDanGoodsFenLeiFarther> {

    List<DianDanGoodsFenLeiFarther> findByGoodsId(String goods_id);
}

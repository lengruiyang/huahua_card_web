package cn.huiounet.service;

import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiFarther;

import java.util.List;

public interface DianDanGoodsFenLeiFartherService {

    List<DianDanGoodsFenLeiFarther> findByGoodsId(String goods_id);
}

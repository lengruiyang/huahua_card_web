package cn.huiounet.dao;

import cn.huiounet.pojo.goods.GoodsColor;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsColorMapper extends Mapper<GoodsColor> {

    List<GoodsColor> findGoodsColor(String goods_id);

    GoodsColor findById(String id);
}

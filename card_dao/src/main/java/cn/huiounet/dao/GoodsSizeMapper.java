package cn.huiounet.dao;

import cn.huiounet.pojo.goods.GoodsSize;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsSizeMapper extends Mapper<GoodsSize> {

    List<GoodsSize> findGoodsSize(String color_id);

    GoodsSize findById(String id);
}

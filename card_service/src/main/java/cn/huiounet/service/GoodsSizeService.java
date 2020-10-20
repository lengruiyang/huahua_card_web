package cn.huiounet.service;

import cn.huiounet.pojo.goods.GoodsSize;

import java.util.List;

public interface GoodsSizeService  {

    List<GoodsSize> findGoodsSize(String color_id);

    GoodsSize findById(String id);

    void saveSize(GoodsSize goodsSize);

}

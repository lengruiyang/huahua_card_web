package cn.huiounet.service;

import cn.huiounet.pojo.goods.GoodsColor;

import java.util.List;

public interface GoodsColorService {

    List<GoodsColor> findGoodsColor(String goods_id);

    GoodsColor findById(String id);

    void saveGoodsColor(GoodsColor goodsColor);

    int findMaxId();

}

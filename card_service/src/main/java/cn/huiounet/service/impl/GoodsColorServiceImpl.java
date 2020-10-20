package cn.huiounet.service.impl;

import cn.huiounet.dao.GoodsColorMapper;
import cn.huiounet.pojo.goods.GoodsColor;
import cn.huiounet.service.GoodsColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsColorServiceImpl implements GoodsColorService {
    @Autowired
    private GoodsColorMapper goodsColorMapper;

    @Override
    public List<GoodsColor> findGoodsColor(String goods_id) {
        return goodsColorMapper.findGoodsColor(goods_id);
    }

    @Override
    public GoodsColor findById(String id) {
        return goodsColorMapper.findById(id);
    }

    @Override
    public void saveGoodsColor(GoodsColor goodsColor) {
        goodsColorMapper.insert(goodsColor);
    }

    @Override
    public int findMaxId() {
        return goodsColorMapper.findMaxId();
    }
}

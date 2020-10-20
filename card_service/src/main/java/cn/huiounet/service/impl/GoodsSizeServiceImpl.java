package cn.huiounet.service.impl;

import cn.huiounet.dao.GoodsSizeMapper;
import cn.huiounet.pojo.goods.GoodsSize;
import cn.huiounet.service.GoodsSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSizeServiceImpl implements GoodsSizeService{
    @Autowired
    private GoodsSizeMapper goodsSizeMapper;

    @Override
    public List<GoodsSize> findGoodsSize(String color_id) {
        return goodsSizeMapper.findGoodsSize(color_id);
    }

    @Override
    public GoodsSize findById(String id) {
        return goodsSizeMapper.findById(id);
    }

    @Override
    public void saveSize(GoodsSize goodsSize) {
        goodsSizeMapper.insert(goodsSize);
    }
}

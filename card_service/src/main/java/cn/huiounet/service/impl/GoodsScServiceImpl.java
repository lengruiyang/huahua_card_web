package cn.huiounet.service.impl;

import cn.huiounet.dao.GoodsScMapper;
import cn.huiounet.pojo.goods.GoodsSc;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.service.GoodsScService;
import cn.huiounet.service.GoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsScServiceImpl implements GoodsScService {
    @Autowired
    private GoodsScMapper goodsScMapper;

    @Override
    public GoodsSc findSc(String goods_id, String user_id) {
        return goodsScMapper.findSc(goods_id, user_id);
    }

    @Override
    public void deleteById(String goods_id, String user_id) {
        goodsScMapper.deleteById(goods_id, user_id);
    }


    @Override
    public void saveGoodsSc(GoodsSc goodsSc) {
        goodsScMapper.insert(goodsSc);
    }

    @Override
    public List<GoodsSc> findScByUser(String user_id,int start,int size) {
        return goodsScMapper.findScByUser(user_id,start,size);
    }
}

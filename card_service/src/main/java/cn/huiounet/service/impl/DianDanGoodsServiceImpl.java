package cn.huiounet.service.impl;

import cn.huiounet.dao.DianDanGoodsMapper;
import cn.huiounet.pojo.diandan.DianDanGoods;
import cn.huiounet.service.DianDanGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DianDanGoodsServiceImpl implements DianDanGoodsService {
    @Autowired
    private DianDanGoodsMapper dianDanGoodsMapper;

    @Override
    public List<DianDanGoods> findByFenLeiId(String id) {
        return dianDanGoodsMapper.findByFenLeiId(id);
    }

    @Override
    public DianDanGoods findById(int id) {
        return dianDanGoodsMapper.findById(id);
    }
}

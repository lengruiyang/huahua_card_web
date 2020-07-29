package cn.huiounet.service.impl;

import cn.huiounet.dao.GoodsSysMapper;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.service.GoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSysServiceImpl implements GoodsSysService {
    @Autowired
    private GoodsSysMapper goodsSysMapper;

    @Override
    public GoodsSys findId(String id) {
        return goodsSysMapper.findId(id);
    }

    @Override
    public List<GoodsSys> findNewGoods() {
        return goodsSysMapper.findNewGoods();
    }

    @Override
    public List<GoodsSys> SearchGoods(String goods_name) {
        return goodsSysMapper.SearchGoods(goods_name);
    }

    @Override
    public List<GoodsSys> findAll(int start, int pageSize) {
        return goodsSysMapper.findAll(start, pageSize);
    }
}

package cn.huiounet.service.impl;

import cn.huiounet.dao.GoodsRootMapper;
import cn.huiounet.pojo.root.GoodsRoot;
import cn.huiounet.service.GoodsRootService;
import cn.huiounet.service.GoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class GoodsRootServiceImpl implements GoodsRootService {
    @Autowired
    private GoodsRootMapper goodsRootMapper;

    @Override
    public void save(GoodsRoot goodsRoot) {
        goodsRootMapper.insert(goodsRoot);
    }

    @Override
    public List<GoodsRoot> findByUserId(String user_id, int start, int size) {
        return goodsRootMapper.findByUserId(user_id, start, size);
    }
}

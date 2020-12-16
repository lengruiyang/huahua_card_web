package cn.huiounet.service.impl;

import cn.huiounet.dao.DianDanGoodsFenLeiFartherMapper;
import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiFarther;
import cn.huiounet.service.DianDanGoodsFenLeiFartherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DianDanGoodsFenLeiFartherServiceImpl implements DianDanGoodsFenLeiFartherService {
    @Autowired
    private DianDanGoodsFenLeiFartherMapper dianDanGoodsFenLeiFartherMapper;

    @Override
    public List<DianDanGoodsFenLeiFarther> findByGoodsId(String goods_id) {
        return dianDanGoodsFenLeiFartherMapper.findByGoodsId(goods_id);
    }
}

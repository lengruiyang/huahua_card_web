package cn.huiounet.service.impl;

import cn.huiounet.dao.DianDanGoodsFenLeiSonMapper;
import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiSon;
import cn.huiounet.service.DianDanGoodsFenLeiSonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DianDanGoodsFenLeiSonServiceImpl implements DianDanGoodsFenLeiSonService {
    @Autowired
    private DianDanGoodsFenLeiSonMapper dianDanGoodsFenLeiSonMapper;

    @Override
    public List<DianDanGoodsFenLeiSon> findByFatherId(String farther_id) {
        return dianDanGoodsFenLeiSonMapper.findByFatherId(farther_id);
    }
}

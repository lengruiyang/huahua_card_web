package cn.huiounet.service.impl;

import cn.huiounet.dao.PingJIaHFMapper;
import cn.huiounet.pojo.pingjia.PingJIaHF;
import cn.huiounet.service.PingJIaHFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PingJIaHFServiceImpl implements PingJIaHFService {
    @Autowired
    private PingJIaHFMapper pingJIaHFMapper;

    @Override
    public List<PingJIaHF> findByPjId(String pj_id,int start,int size) {
        return pingJIaHFMapper.findByPjId(pj_id, start, size);
    }

    @Override
    public void savePjHf(PingJIaHF pingJIaHF) {
        pingJIaHFMapper.insert(pingJIaHF);
    }
}

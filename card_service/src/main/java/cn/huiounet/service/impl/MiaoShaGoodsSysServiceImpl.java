package cn.huiounet.service.impl;

import cn.huiounet.dao.MiaoShaGoodsSysMapper;
import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import cn.huiounet.service.MiaoShaGoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiaoShaGoodsSysServiceImpl implements MiaoShaGoodsSysService {
    @Autowired
    private MiaoShaGoodsSysMapper miaoShaGoodsSysMapper;

    @Override
    public List<MiaoShaGoodsSys> findAll() {
        return miaoShaGoodsSysMapper.selectAll();
    }

    @Override
    public void updateStartTime(String start_time, String id) {
        miaoShaGoodsSysMapper.updateStartTime(start_time, id);
    }

    @Override
    public MiaoShaGoodsSys findById(String id) {
        return miaoShaGoodsSysMapper.findById(id);
    }
}

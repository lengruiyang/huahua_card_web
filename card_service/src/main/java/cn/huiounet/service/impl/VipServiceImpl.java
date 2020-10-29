package cn.huiounet.service.impl;

import cn.huiounet.dao.VipMapper;
import cn.huiounet.pojo.vip.Vip;
import cn.huiounet.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipServiceImpl implements VipService{
    @Autowired
    private VipMapper vipMapper;


    @Override
    public Vip findById(String id) {
        return vipMapper.findById(id);
    }
}

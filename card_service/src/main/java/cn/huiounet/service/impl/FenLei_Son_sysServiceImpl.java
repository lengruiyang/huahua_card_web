package cn.huiounet.service.impl;

import cn.huiounet.dao.FenLei_Son_sysMapper;
import cn.huiounet.pojo.fenlei.FenLei_Son_sys;
import cn.huiounet.service.FenLei_Son_sysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class FenLei_Son_sysServiceImpl implements FenLei_Son_sysService {
    @Autowired
    private FenLei_Son_sysMapper fenLei_son_sysMapper;

    @Override
    public List<FenLei_Son_sys> findByFartherId(String farther_id) {
        return fenLei_son_sysMapper.findByFartherId(farther_id);
    }
}

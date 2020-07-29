package cn.huiounet.service.impl;

import cn.huiounet.dao.FenLei_Farther_sysMapper;
import cn.huiounet.pojo.fenlei.FenLei_Farther_sys;
import cn.huiounet.service.FenLei_Farther_sysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FenLei_Farther_sysServiceImpl implements FenLei_Farther_sysService{
    @Autowired
    private FenLei_Farther_sysMapper fenLei_farther_sysMapper;

    @Override
    public FenLei_Farther_sys findById(String id) {
        return fenLei_farther_sysMapper.findById(id);
    }

    @Override
    public List<FenLei_Farther_sys> findAll() {
        return fenLei_farther_sysMapper.selectAll();
    }
}

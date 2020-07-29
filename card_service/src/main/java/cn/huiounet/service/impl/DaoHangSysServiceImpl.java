package cn.huiounet.service.impl;

import cn.huiounet.dao.DaoHangSysMapper;
import cn.huiounet.pojo.daohang.DaoHangSys;
import cn.huiounet.service.DaoHangSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaoHangSysServiceImpl implements DaoHangSysService {
    @Autowired
    private DaoHangSysMapper daoHangSysMapper;

    @Override
    public List<DaoHangSys> findAll() {
        return daoHangSysMapper.selectAll();
    }

    @Override
    public List<DaoHangSys> findByStatus() {
        return daoHangSysMapper.findByStatus();
    }
}

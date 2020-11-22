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

    @Override
    public void save(DaoHangSys daoHangSys) {
        daoHangSysMapper.insert(daoHangSys);
    }

    @Override
    public void updateById(String status, int id) {
        daoHangSysMapper.updateById(status, id);
    }

    @Override
    public void updateMessById(String name, String img, String to_url, int id) {
        daoHangSysMapper.updateMessById(name, img, to_url, id);
    }

    @Override
    public void deleteById(int id) {
        daoHangSysMapper.deleteById(id);
    }

    @Override
    public DaoHangSys findById(int id) {
        return daoHangSysMapper.findById(id);
    }
}

package cn.huiounet.service.impl;

import cn.huiounet.dao.JiFenGoodsFenLeiSysMapper;
import cn.huiounet.pojo.renwujifen.JiFenGoodsFenLeiSys;
import cn.huiounet.service.JiFenGoodsFenLeiSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiFenGoodsFenLeiSysServiceImpl implements JiFenGoodsFenLeiSysService {
    @Autowired
    private JiFenGoodsFenLeiSysMapper jiFenGoodsFenLeiSysMapper;


    @Override
    public List<JiFenGoodsFenLeiSys> findAllStatus() {
        return jiFenGoodsFenLeiSysMapper.findAllStatus();
    }
}

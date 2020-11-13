package cn.huiounet.service.impl;

import cn.huiounet.dao.JiFenGoodsSysMapper;
import cn.huiounet.pojo.renwujifen.JiFenGoodsSys;
import cn.huiounet.service.JiFenGoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiFenGoodsSysServiceImpl implements JiFenGoodsSysService {
    @Autowired
    private JiFenGoodsSysMapper jiFenGoodsSysMapper;
    @Override
    public List<JiFenGoodsSys> findByFenLei(String jifen_fen_lei, int start, int size) {
        return jiFenGoodsSysMapper.findByFenLei(jifen_fen_lei, start, size);
    }

    @Override
    public JiFenGoodsSys findById(String id) {
        return jiFenGoodsSysMapper.findById(id);
    }
}

package cn.huiounet.service.impl;

import cn.huiounet.dao.AddressTipMapper;
import cn.huiounet.pojo.address.AddressTip;
import cn.huiounet.service.AddressTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressTipServiceImpl implements AddressTipService{
    @Autowired
    private AddressTipMapper addressTipMapper;

    @Override
    public List<AddressTip> findAll() {
        return addressTipMapper.selectAll();
    }
}

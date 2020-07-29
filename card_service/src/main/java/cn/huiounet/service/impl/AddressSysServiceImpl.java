package cn.huiounet.service.impl;

import cn.huiounet.dao.AddressSysMapper;
import cn.huiounet.pojo.address.AddressSys;
import cn.huiounet.service.AddressSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressSysServiceImpl implements AddressSysService {
    @Autowired
    private AddressSysMapper addressSysMapper;

    @Override
    public List<AddressSys> findByUser_id(String user_id) {
        return addressSysMapper.findByUser_id(user_id);
    }

    @Override
    public void deleteById(String id) {
        addressSysMapper.deleteById(id);
    }

    @Override
    public void saveAddress(AddressSys addressSys) {
        addressSysMapper.insert(addressSys);
    }

    @Override
    public AddressSys findByStatus(String user_id, String status) {
        return addressSysMapper.findByStatus(user_id, status);
    }

    @Override
    public AddressSys findById(String id) {
        return addressSysMapper.findById(id);
    }
}

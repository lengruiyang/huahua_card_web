package cn.huiounet.service;

import cn.huiounet.pojo.address.AddressSys;

import java.util.List;

public interface AddressSysService {

    List<AddressSys> findByUser_id(String user_id);

    void deleteById(String id);

    void saveAddress(AddressSys addressSys);

    AddressSys findByStatus(String user_id,String status);

    AddressSys findById(String id);
}

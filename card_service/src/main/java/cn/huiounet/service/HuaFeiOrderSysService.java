package cn.huiounet.service;

import cn.huiounet.pojo.huafei.HuaFeiOrderSys;

import java.util.List;

public interface HuaFeiOrderSysService {

    HuaFeiOrderSys findByOrderNum(String order_num);

    void updateByOrderNum(String pay_status,String pay_time,String order_num);

    void saveOrder(HuaFeiOrderSys huaFeiOrderSys);

    List<HuaFeiOrderSys> findByUserId(String user_id, int start, int size);

    List<HuaFeiOrderSys> findAll();

    List<HuaFeiOrderSys> findPayButNotSuccess(String is_cz_success);

    void updateById(String is_cz_success,String id);

    void deleteById(String id);
}

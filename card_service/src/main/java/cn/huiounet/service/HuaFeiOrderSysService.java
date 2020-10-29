package cn.huiounet.service;

import cn.huiounet.pojo.huafei.HuaFeiOrderSys;

public interface HuaFeiOrderSysService {

    HuaFeiOrderSys findByOrderNum(String order_num);

    void updateByOrderNum(String pay_status,String pay_time,String order_num);

    void saveOrder(HuaFeiOrderSys huaFeiOrderSys);

}

package cn.huiounet.service;

import cn.huiounet.pojo.fapiao.OrderFPsys;

import java.util.List;

public interface OrderFPsysService {

    OrderFPsys findByOrderNum(String order_num);

    void saveFP(OrderFPsys orderFPsys);

    List<OrderFPsys> findByUserId(String user_id);
}

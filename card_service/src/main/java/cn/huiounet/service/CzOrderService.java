package cn.huiounet.service;

import cn.huiounet.pojo.order.CzOrder;

public interface CzOrderService {

    CzOrder findByUserId(String user_id);

    CzOrder findByOrderNum(String order_num);

    void updateStatus(String status,String order_num);

    void savePay(CzOrder czOrder);

}

package cn.huiounet.service;

import cn.huiounet.pojo.renwujifen.JiFenOrder;

public interface JiFenOrderService {

    JiFenOrder findByOrderNum(String order_num);

    void updateByOrderNum(String is_change,String change_time,String order_num);

    void saveOrder(JiFenOrder jiFenOrder);

    void updateAddressByOrderNum(String address_id,String order_num);

}

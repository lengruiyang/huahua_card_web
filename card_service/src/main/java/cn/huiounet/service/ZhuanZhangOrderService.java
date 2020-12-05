package cn.huiounet.service;

import cn.huiounet.pojo.order.ZhuanZhangOrder;

public interface ZhuanZhangOrderService {

    ZhuanZhangOrder findByOrderNum(String order_num);

    void updateByOrderNum(String status,String order_num);

    void saveOrder(ZhuanZhangOrder zhuanZhangOrder);
}

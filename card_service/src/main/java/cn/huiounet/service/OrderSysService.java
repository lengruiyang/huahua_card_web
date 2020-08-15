package cn.huiounet.service;

import cn.huiounet.pojo.order.OrderSys;

import java.util.List;

public interface OrderSysService {

    OrderSys findByOrderNum(String order_num);

    void saveOrder(OrderSys orderSys);

    void updataPayStatusByOrderNum(String pay_status, String order_num);

    void updateNotic(String goods_notic, String order_num);

    void updateAddress(String address_num, String order_num);

    void updatePj(String is_pj,String order_num);

    void updataPayTime(String pay_time, String order_num);

    void updateFaHuo(String fa_huo_time, String fa_huo_num, String order_num);

    List<OrderSys> findOrderStatus(String user_id, String pay_status,int start,int size);

    List<OrderSys> findAll(String user_id, int start,int size);

    int findAllNum(String user_id,String pay_status);
}

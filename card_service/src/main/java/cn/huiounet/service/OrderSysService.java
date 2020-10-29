package cn.huiounet.service;

import cn.huiounet.pojo.order.OrderSys;

import java.util.List;

public interface OrderSysService {

    OrderSys findByOrderNum(String order_num);

    void updateYouHui(String youhui_status,String youhui_much,String youhuiquan_id,String order_num);

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

    void updateFP(String fa_piao,String order_num);

    void updatePayNum(String pay_num,String order_num);

    void updatePayNumById(String pay_num,String id);

    List<OrderSys> findPayNumList(String pay_num);

    void updateAll_pay(String all_pay,String order_num);

    void deleteByOrderNum(String order_num);

    List<OrderSys> findOrderNumByUserId(String user_id);

    void updatePayWay(String pay_way,String order_num);

    void updateTk(String is_tk,String tk_money,String tk_time, String order_num);

    List<OrderSys> searchOrder(String user_id,String order_num,String shop_name,int start,int size);

    List<OrderSys> findZhOrderNum(String zh_order_num);
}

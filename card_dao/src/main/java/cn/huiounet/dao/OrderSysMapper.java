package cn.huiounet.dao;

import cn.huiounet.pojo.order.OrderSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderSysMapper extends Mapper<OrderSys>{

    List<OrderSys> findOrderNumByUserId(String user_id);

    OrderSys findByOrderNum(String order_num);

    List<OrderSys> findPayNumList(String pay_num);

    int findAllNum(@Param(value = "user_id")String user_id,@Param(value = "pay_status")String pay_status);

    void updataPayStatusByOrderNum(@Param(value = "pay_status")String pay_status,@Param(value = "order_num")String order_num);

    void updateNotic(@Param(value = "goods_notic")String goods_notic,@Param(value = "order_num")String order_num);

    void updateAddress(@Param(value = "address_num")String address_num,@Param(value = "order_num")String order_num);

    void updataPayTime(@Param(value = "pay_time")String pay_time,@Param(value = "order_num")String order_num);

    void updatePj(@Param(value = "is_pj")String is_pj,@Param(value = "order_num")String order_num);

    void updateFP(@Param(value = "fa_piao")String fa_piao,@Param(value = "order_num")String order_num);
    void updatePayNum(@Param(value = "pay_num")String pay_num,@Param(value = "order_num")String order_num);
    void updatePayNumById(@Param(value = "pay_num")String pay_num,@Param(value = "id")String id);
    void updateAll_pay(@Param(value = "all_pay")String all_pay,@Param(value = "order_num")String order_num);

    void updateFaHuo(@Param(value = "fa_huo_time")String fa_huo_time,@Param(value = "fa_huo_num")String fa_huo_num,@Param(value = "order_num")String order_num);


    List<OrderSys> findOrderStatus(@Param(value = "user_id")String user_id,@Param(value = "pay_status")String pay_status,@Param(value = "start")int start,@Param(value = "size")int size);

    List<OrderSys> findAll(@Param(value = "user_id") String user_id,@Param(value = "start")int start,@Param(value = "size")int size);

    void deleteByOrderNum(String order_num);
}

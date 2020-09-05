package cn.huiounet.dao;


import cn.huiounet.pojo.order.OrderSellAfter;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderSellAfterMapper extends Mapper<OrderSellAfter> {

    OrderSellAfter findByOrderNum(String order_num);

    int findByUserIdNum(String user_id);

    List<OrderSellAfter> findByUserId(@Param(value = "user_id") String user_id,@Param(value = "start")int start,@Param(value = "size")int size);

    void updateStatus(@Param(value = "status")String status,@Param(value = "result")String result,@Param(value = "work_time")String work_time,@Param(value = "order_num")String order_num);

    void updateUserThink(@Param(value = "user_think")String user_think,@Param(value = "order_num")String order_num);
}

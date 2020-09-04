package cn.huiounet.dao;


import cn.huiounet.pojo.order.OrderSellAfter;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderSellAfterMapper extends Mapper<OrderSellAfter> {

    OrderSellAfter findByOrderNum(String order_num);

    List<OrderSellAfter> findByUserId(String user_id);

    void updateStatus(@Param(value = "status")String status,@Param(value = "result")String result,@Param(value = "order_num")String order_num);
}

package cn.huiounet.dao;

import cn.huiounet.pojo.order.OrderAddress;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OrderAddressMapper extends Mapper<OrderAddress> {

    OrderAddress findByOrderNum(String order_num);

    void updateByOrderNum(@Param(value = "tip")String tip,@Param(value = "user_name")String user_name,@Param(value = "user_phone")String user_phone,@Param(value = "user_address")String user_address,@Param(value = "order_num")String order_num);
}

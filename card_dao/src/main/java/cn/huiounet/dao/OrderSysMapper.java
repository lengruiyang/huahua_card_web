package cn.huiounet.dao;

import cn.huiounet.pojo.order.OrderSys;
import tk.mybatis.mapper.common.Mapper;

public interface OrderSysMapper extends Mapper<OrderSys>{

    OrderSys findByOrderNum(String order_num);

}

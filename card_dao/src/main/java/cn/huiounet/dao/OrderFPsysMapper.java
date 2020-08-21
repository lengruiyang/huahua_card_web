package cn.huiounet.dao;

import cn.huiounet.pojo.fapiao.OrderFPsys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderFPsysMapper extends Mapper<OrderFPsys> {


    OrderFPsys findByOrderNum(String order_num);

    List<OrderFPsys> findByUserId(String user_id);
}

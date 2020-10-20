package cn.huiounet.dao;

import cn.huiounet.pojo.order.CzOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface CzOrderMapper extends Mapper<CzOrder> {

    CzOrder findByUserId(String user_id);

    CzOrder findByOrderNum(String order_num);

    void updateStatus(@Param(value = "status")String status,@Param(value = "order_num")String order_num);

}

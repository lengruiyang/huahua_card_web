package cn.huiounet.dao;

import cn.huiounet.pojo.order.ZhuanZhangOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ZhuanZhangOrderMapper extends Mapper<ZhuanZhangOrder> {

    ZhuanZhangOrder findByOrderNum(String order_num);

    void updateByOrderNum(@Param(value = "status")String status,@Param(value = "order_num")String order_num);
}

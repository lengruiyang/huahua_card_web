package cn.huiounet.dao;

import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface HuaFeiOrderSysMapper extends Mapper<HuaFeiOrderSys> {

    HuaFeiOrderSys findByOrderNum(String order_num);

    void updateByOrderNum(@Param(value = "pay_status")String pay_status,@Param(value = "pay_time")String pay_time,@Param(value = "order_num")String order_num);

}

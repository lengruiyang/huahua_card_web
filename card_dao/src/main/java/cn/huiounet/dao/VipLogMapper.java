package cn.huiounet.dao;

import cn.huiounet.pojo.vip.VipLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface VipLogMapper extends Mapper<VipLog> {

    void updateByOrderNum(@Param(value = "status")String status,@Param(value = "order_num")String order_num);

    VipLog findByOrderNum(String order_num);

}

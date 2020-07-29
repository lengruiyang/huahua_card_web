package cn.huiounet.dao;

import cn.huiounet.pojo.coupon.CouponSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouponSysMapper extends Mapper<CouponSys> {

    List<CouponSys> findByUser(String user_id);

    void updateById(@Param(value = "status")String status,@Param(value = "id")String id);

    CouponSys findById(String id);

    void updateTime(@Param(value = "start_time")String start_time,@Param(value = "last_time")String last_time,@Param(value = "id")String id);
}

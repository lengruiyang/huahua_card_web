package cn.huiounet.service;

import cn.huiounet.pojo.coupon.CouponSys;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponSysService {

    List<CouponSys> findByUser(String user_id);

    void updateById(@Param(value = "status")String status, @Param(value = "id")String id);

    CouponSys findById(String id);

    void updateTime(String start_time,String last_time,String id);
}

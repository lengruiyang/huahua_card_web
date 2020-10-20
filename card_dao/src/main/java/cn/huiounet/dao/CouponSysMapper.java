package cn.huiounet.dao;

import cn.huiounet.pojo.coupon.CouPon;
import cn.huiounet.pojo.coupon.CouponSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouponSysMapper extends Mapper<CouponSys> {

    List<CouponSys> findByUser(@Param(value = "user_id") String user_id, @Param(value = "status") String status);

    List<CouponSys> findCouPonUse(@Param(value = "user_id") String user_id, @Param(value = "goods_id") String goods_id);

    List<CouponSys> findCouPonUseShop(@Param(value = "user_id") String user_id, @Param(value = "shop_id") String shop_id);


    void updateById(@Param(value = "status") String status, @Param(value = "id") String id);

    CouponSys findById(String id);

    void updateTime(@Param(value = "start_time") String start_time, @Param(value = "last_time") String last_time, @Param(value = "id") String id);

    CouponSys findByCouPon(String coupon_id);

    CouponSys findByIdAndUser(@Param(value = "coupon_id") String coupon_id, @Param(value = "user_id") String user_id);
}

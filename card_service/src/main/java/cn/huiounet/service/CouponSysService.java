package cn.huiounet.service;

import cn.huiounet.pojo.coupon.CouponSys;

import java.util.List;

public interface CouponSysService {

    List<CouponSys> findByUser(String user_id, String status);

    void updateById(String status, String id);

    List<CouponSys> findCouPonUse(String user_id, String goods_id);

    List<CouponSys> findCouPonUseShop(String user_id, String shop_id);

    CouponSys findById(String id);

    void updateTime(String start_time, String last_time, String id);

    CouponSys findByCouPon(String coupon_id);

    void saveCoupon(CouponSys couponSys);

    CouponSys findByIdAndUser(String coupon_id, String user_id);
}

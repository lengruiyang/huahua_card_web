package cn.huiounet.service.impl;

import cn.huiounet.dao.CouponSysMapper;
import cn.huiounet.pojo.coupon.CouponSys;
import cn.huiounet.service.CouponSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponSysServiceImpl implements CouponSysService {
    @Autowired
    private CouponSysMapper couponSysMapper;
    @Override
    public List<CouponSys> findByUser(String user_id,String status) {
        return couponSysMapper.findByUser(user_id,status);
    }

    @Override
    public void updateById(String status, String id) {
        couponSysMapper.updateById(status,id);
    }

    @Override
    public List<CouponSys> findCouPonUse(String user_id, String goods_id) {
        return couponSysMapper.findCouPonUse(user_id, goods_id);
    }

    @Override
    public List<CouponSys> findCouPonUseShop(String user_id, String shop_id) {
        return couponSysMapper.findCouPonUseShop(user_id, shop_id);
    }

    @Override
    public CouponSys findById(String id) {
        return couponSysMapper.findById(id);
    }

    @Override
    public void updateTime(String start_time, String last_time, String id) {
        couponSysMapper.updateTime(start_time, last_time, id);
    }

    @Override
    public CouponSys findByCouPon(String coupon_id) {
        return couponSysMapper.findByCouPon(coupon_id);
    }

    @Override
    public void saveCoupon(CouponSys couponSys) {
        couponSysMapper.insert(couponSys);
    }

    @Override
    public CouponSys findByIdAndUser(String coupon_id, String user_id) {
        return couponSysMapper.findByIdAndUser(coupon_id, user_id);
    }
}

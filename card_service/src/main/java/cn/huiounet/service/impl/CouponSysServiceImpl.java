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
    public List<CouponSys> findByUser(String user_id) {
        return couponSysMapper.findByUser(user_id);
    }

    @Override
    public void updateById(String status, String id) {
        couponSysMapper.updateById(status,id);
    }

    @Override
    public CouponSys findById(String id) {
        return couponSysMapper.findById(id);
    }

    @Override
    public void updateTime(String start_time, String last_time, String id) {
        couponSysMapper.updateTime(start_time, last_time, id);
    }
}

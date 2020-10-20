package cn.huiounet.service.impl;

import cn.huiounet.dao.CouPonMapper;
import cn.huiounet.pojo.coupon.CouPon;
import cn.huiounet.service.CouPonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouPonServiceImpl implements CouPonService {
    @Autowired
    private CouPonMapper couPonMapper;

    @Override
    public List<CouPon> findBySys() {
        return couPonMapper.findBySys();
    }

    @Override
    public List<CouPon> findBySysGoods(String goods_id) {
        return couPonMapper.findBySysGoods(goods_id);
    }

    @Override
    public CouPon findById(String id) {
        return couPonMapper.findById(id);
    }

    @Override
    public List<CouPon> findByVipSys() {
        return couPonMapper.findByVipSys();
    }

    @Override
    public void updateNum(String num, String id) {
        couPonMapper.updateNum(num, id);
    }

    @Override
    public List<CouPon> findBySysShop(String shop_id) {
        return couPonMapper.findBySysShop(shop_id);
    }
}

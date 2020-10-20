package cn.huiounet.service;

import cn.huiounet.pojo.coupon.CouPon;

import java.util.List;

public interface CouPonService {

    List<CouPon> findBySys();

    List<CouPon> findBySysGoods(String goods_id);

    CouPon findById(String id);

    List<CouPon> findByVipSys();

    void updateNum(String num,String id);

    List<CouPon> findBySysShop(String shop_id);
}

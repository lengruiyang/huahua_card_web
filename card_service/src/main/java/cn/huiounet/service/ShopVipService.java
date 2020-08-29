package cn.huiounet.service;

import cn.huiounet.pojo.shop.ShopVip;

import java.util.List;

public interface ShopVipService {

    List<ShopVip> findByShopId(String shop_id);
}

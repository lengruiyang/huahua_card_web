package cn.huiounet.service.impl;

import cn.huiounet.dao.ShopScMapper;
import cn.huiounet.pojo.shop.ShopSc;
import cn.huiounet.service.ShopScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopScServiceImpl implements ShopScService {
    @Autowired
    private ShopScMapper shopScMapper;

    @Override
    public ShopSc findByUserId(String user_id, String shop_id) {
        return shopScMapper.findByUserId(user_id, shop_id);
    }

    @Override
    public int findByShopId(String shop_id) {
        return shopScMapper.findByShopId(shop_id);
    }

    @Override
    public void saveSc(ShopSc shopSc) {
        shopScMapper.insert(shopSc);
    }

    @Override
    public void deleteSc(String user_id, String shop_id) {
        shopScMapper.deleteSc(user_id, shop_id);
    }
}

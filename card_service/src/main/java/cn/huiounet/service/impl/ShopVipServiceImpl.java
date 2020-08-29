package cn.huiounet.service.impl;

import cn.huiounet.dao.ShopVipMapper;
import cn.huiounet.pojo.shop.ShopVip;
import cn.huiounet.service.ShopVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopVipServiceImpl implements ShopVipService {
    @Autowired
    private ShopVipMapper shopVipMapper;
    @Override
    public List<ShopVip> findByShopId(String shop_id) {
        return shopVipMapper.findByShopId(shop_id);
    }
}

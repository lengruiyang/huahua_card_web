package cn.huiounet.service.impl;

import cn.huiounet.dao.ShopSysMapper;
import cn.huiounet.pojo.shop.ShopSys;
import cn.huiounet.service.ShopSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopSysServiceImpl implements ShopSysService {
    @Autowired
    private ShopSysMapper shopSysMapper;

    @Override
    public ShopSys findByOpenId(String user_open_id) {
        return shopSysMapper.findByOpenId(user_open_id);
    }
}

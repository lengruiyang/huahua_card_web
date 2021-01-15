package cn.huiounet.service.impl;

import cn.huiounet.dao.ShopSysMapper;
import cn.huiounet.pojo.shop.ShopSys;
import cn.huiounet.service.ShopSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopSysServiceImpl implements ShopSysService {
    @Autowired
    private ShopSysMapper shopSysMapper;

    @Override
    public ShopSys findByOpenId(String user_open_id) {
        return shopSysMapper.findByOpenId(user_open_id);
    }

    @Override
    public ShopSys findById(String id) {
        return shopSysMapper.findById(id);
    }

    @Override
    public void updateFans(String fans, String user_open_id) {
        shopSysMapper.updateFans(fans, user_open_id);
    }

    @Override
    public List<ShopSys> findByZiTi(String is_ziti, int start, int size) {
        return shopSysMapper.findByZiTi(is_ziti, start, size);
    }

    @Override
    public Long findAll() {
        int size = shopSysMapper.selectAll().size();

        return (long)size;
    }
}

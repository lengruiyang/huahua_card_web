package cn.huiounet.service.impl;

import cn.huiounet.dao.AddShopMapper;
import cn.huiounet.pojo.shop.AddShop;
import cn.huiounet.service.AddShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/17 16:21
 * @Version 1.0
 **/
@Service
public class AddShopServiceImpl implements AddShopService {
    @Autowired
    private AddShopMapper addShopMapper;

    @Override
    public AddShop findByUserId(String user_id) {
        return addShopMapper.findByUserId(user_id);
    }

    @Override
    public void updateStatusById(String status, int id) {
        addShopMapper.updateStatusById(status, id);
    }

    @Override
    public void saveAddShop(AddShop addShop) {
        addShopMapper.insert(addShop);
    }

    @Override
    public List<AddShop> findPage(int start, int size) {
        return addShopMapper.findPage(start, size);
    }

    @Override
    public Long AllSize() {
        int size = addShopMapper.selectAll().size();
        return Long.valueOf(size);
    }
}

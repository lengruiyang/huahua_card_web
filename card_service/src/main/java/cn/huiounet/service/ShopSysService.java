package cn.huiounet.service;

import cn.huiounet.pojo.shop.ShopSys;

import java.util.List;

public interface ShopSysService {

    ShopSys findByOpenId(String user_open_id);

    ShopSys findById(String id);

    void updateFans(String fans,String user_open_id);
}

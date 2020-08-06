package cn.huiounet.service;

import cn.huiounet.pojo.shop.ShopSys;

public interface ShopSysService {

    ShopSys findByOpenId(String user_open_id);
}

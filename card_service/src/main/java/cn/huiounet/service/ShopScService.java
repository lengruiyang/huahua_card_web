package cn.huiounet.service;

import cn.huiounet.pojo.shop.ShopSc;
import org.apache.ibatis.annotations.Param;

public interface ShopScService {
    ShopSc findByUserId(String user_id,String shop_id);

    int findByShopId(String shop_id);

    void saveSc(ShopSc shopSc);

    void deleteSc(String user_id,String shop_id);
}

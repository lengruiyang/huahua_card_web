package cn.huiounet.service;

import cn.huiounet.pojo.goods.GoodsSc;

public interface GoodsScService {
    GoodsSc findSc(String goods_id,String user_id);

    void deleteById(String goods_id,String user_id);

    void saveGoodsSc(GoodsSc goodsSc);
}

package cn.huiounet.service;

import cn.huiounet.pojo.goods.GoodsSys;

import java.util.List;

public interface GoodsSysService {


    GoodsSys findId(String id);

    List<GoodsSys> findNewGoods();

    List<GoodsSys> SearchGoods(String goods_name);

    List<GoodsSys> findAll(int start,int pageSize);

    List<GoodsSys> findByShop_id(String shop_id);
}

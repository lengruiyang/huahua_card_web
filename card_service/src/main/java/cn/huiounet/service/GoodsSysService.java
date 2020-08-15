package cn.huiounet.service;

import cn.huiounet.pojo.goods.GoodsSys;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSysService {


    GoodsSys findId(String id);

    List<GoodsSys> findBySell(int start,int size);

    List<GoodsSys> findNewGoods();

    List<GoodsSys> findByLike(int start,int size);

    void updateSell_many(String sell_many,String id);

    List<GoodsSys> SearchGoods(String goods_name);

    List<GoodsSys> findAll(int start,int pageSize);

    List<GoodsSys> findByShop_id(String shop_id);

    void updateLike(String like_many,String id);
}

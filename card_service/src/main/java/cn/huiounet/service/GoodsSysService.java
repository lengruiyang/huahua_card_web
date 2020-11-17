package cn.huiounet.service;

import cn.huiounet.pojo.goods.GoodsSys;

import java.util.List;

public interface GoodsSysService {


    GoodsSys findId(String id);

    List<GoodsSys> findBySell(int start, int size);

    List<GoodsSys> findNewGoods();


    void updateJiFen(String ji_fen, String id);

    List<GoodsSys> findByLike(int start, int size);

    void updateSell_many(String sell_many, String id);

    List<GoodsSys> SearchGoods(String goods_name);

    List<GoodsSys> findAll(int start, int pageSize);

    List<GoodsSys> findByShop_id(String shop_id);

    void updateLike(String like_many, String id);

    List<GoodsSys> findByShopIdOrderBySellMany(String shop_id, int stsrt, int size);

    List<GoodsSys> findByShopIdOrderByLike(String shop_id, int stsrt, int size);

    List<GoodsSys> findByShopIdOrderByPriceDESC(String shop_id, int stsrt, int size);

    List<GoodsSys> findByShopIdOrderByPriceASC(String shop_id, int stsrt, int size);

    List<GoodsSys> findByShopId(String shop_id, int stsrt, int size);

    List<GoodsSys> findMiaoShaGoodsList(String miaosha_id, int start, int size);

    void updateKuCun(String kucun, String id);

    void saveGoodsSys(GoodsSys goodsSys);

    int findMaxId();

    int findAllSys();

    List<GoodsSys> searchGoods(String goods_name, int start, int size);
}

package cn.huiounet.dao;

import cn.huiounet.pojo.goods.GoodsSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsSysMapper extends Mapper<GoodsSys> {

    GoodsSys findId(String id);

    List<GoodsSys> findNewGoods();

    int findMaxId();

    List<GoodsSys> searchGoods(@Param(value = "goods_name") String goods_name, @Param(value = "start") int start, @Param(value = "size") int size);

    void updateSell_many(@Param(value = "sell_many") String sell_many, @Param(value = "id") String id);

    void updateJiFen(@Param(value = "ji_fen") String ji_fen, @Param(value = "id") String id);

    List<GoodsSys> findByLike(@Param(value = "start") int start, @Param(value = "size") int size);

    List<GoodsSys> findBySell(@Param(value = "start") int start, @Param(value = "size") int size);

    void updateLike(@Param(value = "like_many") String like_many, @Param(value = "id") String id);

    List<GoodsSys> SearchGoods(String goods_name);

    List<GoodsSys> findAll(@Param(value = "start") int start, @Param(value = "pageSize") int pageSize);

    List<GoodsSys> findByShop_id(String shop_id);

    List<GoodsSys> findByShopIdOrderBySellMany(@Param(value = "shop_id") String shop_id, @Param(value = "start") int stsrt, @Param(value = "size") int size);

    List<GoodsSys> findByShopIdOrderByLike(@Param(value = "shop_id") String shop_id, @Param(value = "start") int stsrt, @Param(value = "size") int size);

    List<GoodsSys> findByShopIdOrderByPriceDESC(@Param(value = "shop_id") String shop_id, @Param(value = "start") int stsrt, @Param(value = "size") int size);

    List<GoodsSys> findByShopIdOrderByPriceASC(@Param(value = "shop_id") String shop_id, @Param(value = "start") int stsrt, @Param(value = "size") int size);

    List<GoodsSys> findByShopId(@Param(value = "shop_id") String shop_id, @Param(value = "start") int stsrt, @Param(value = "size") int size);

    List<GoodsSys> findMiaoShaGoodsList(@Param(value = "miaosha_id") String miaosha_id, @Param(value = "start") int start, @Param(value = "size") int size);

    void updateKuCun(@Param(value = "kucun") String kucun, @Param(value = "id") String id);


}

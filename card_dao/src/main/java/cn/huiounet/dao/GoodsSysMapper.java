package cn.huiounet.dao;

import cn.huiounet.pojo.goods.GoodsSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsSysMapper extends Mapper<GoodsSys> {

    GoodsSys findId(String id);

    List<GoodsSys> findNewGoods();

    void updateSell_many(@Param(value = "sell_many")String sell_many,@Param(value = "id")String id);

    List<GoodsSys> findByLike(@Param(value = "start")int start,@Param(value = "size")int size);

    List<GoodsSys> findBySell(@Param(value = "start")int start,@Param(value = "size")int size);

    void updateLike(@Param(value = "like_many")String like_many,@Param(value = "id")String id);

    List<GoodsSys> SearchGoods(String goods_name);

    List<GoodsSys> findAll(@Param(value = "start")int start,@Param(value = "pageSize")int pageSize);

    List<GoodsSys> findByShop_id(String shop_id);
}

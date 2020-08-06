package cn.huiounet.dao;

import cn.huiounet.pojo.goods.GoodsSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsSysMapper extends Mapper<GoodsSys> {

    GoodsSys findId(String id);

    List<GoodsSys> findNewGoods();

    List<GoodsSys> SearchGoods(String goods_name);

    List<GoodsSys> findAll(@Param(value = "start")int start,@Param(value = "pageSize")int pageSize);

    List<GoodsSys> findByShop_id(String shop_id);
}

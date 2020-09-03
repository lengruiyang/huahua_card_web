package cn.huiounet.dao;

import cn.huiounet.pojo.shop.ShopSc;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopScMapper extends Mapper<ShopSc> {

    ShopSc findByUserId(@Param(value = "user_id")String user_id,@Param(value = "shop_id")String shop_id);

    int findByShopId(String shop_id);

    void deleteSc(@Param(value = "user_id")String user_id,@Param(value = "shop_id")String shop_id);

}

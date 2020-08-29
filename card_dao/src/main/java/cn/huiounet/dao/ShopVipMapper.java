package cn.huiounet.dao;

import cn.huiounet.pojo.shop.ShopVip;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopVipMapper extends Mapper<ShopVip> {

    List<ShopVip> findByShopId(String shop_id);
}

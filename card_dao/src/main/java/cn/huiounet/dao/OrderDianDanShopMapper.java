package cn.huiounet.dao;

import cn.huiounet.pojo.diandan.OrderDianDanShop;
import tk.mybatis.mapper.common.Mapper;

public interface OrderDianDanShopMapper extends Mapper<OrderDianDanShop> {

    OrderDianDanShop findById(int id);
}

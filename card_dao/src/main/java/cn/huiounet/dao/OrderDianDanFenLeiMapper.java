package cn.huiounet.dao;

import cn.huiounet.pojo.diandan.OrderDianDanFenLei;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderDianDanFenLeiMapper extends Mapper<OrderDianDanFenLei> {

    List<OrderDianDanFenLei> findByShopId(String id);

}

package cn.huiounet.service;

import cn.huiounet.pojo.diandan.OrderDianDanFenLei;

import java.util.List;

public interface OrderDianDanFenLeiService {

    List<OrderDianDanFenLei> findByShopId(String id);

}

package cn.huiounet.service;

import cn.huiounet.pojo.diandan.OrderDianDanQrCode;

import java.util.List;

public interface OrderDianDanQrCodeService {

    List<OrderDianDanQrCode> findByShopId(String shop_id);
}

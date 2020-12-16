package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderDianDanQrCodeMapper;
import cn.huiounet.pojo.diandan.OrderDianDanQrCode;
import cn.huiounet.service.OrderDianDanQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDianDanQrCodeServiceImpl implements OrderDianDanQrCodeService {
    @Autowired
    private OrderDianDanQrCodeMapper orderDianDanQrCodeMapper;

    @Override
    public List<OrderDianDanQrCode> findByShopId(String shop_id) {
        return orderDianDanQrCodeMapper.findByShopId(shop_id);
    }
}

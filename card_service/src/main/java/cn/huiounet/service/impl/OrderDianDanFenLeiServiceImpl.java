package cn.huiounet.service.impl;

import cn.huiounet.dao.OrderDianDanFenLeiMapper;
import cn.huiounet.pojo.diandan.OrderDianDanFenLei;
import cn.huiounet.service.OrderDianDanFenLeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDianDanFenLeiServiceImpl implements OrderDianDanFenLeiService {
    @Autowired
    private OrderDianDanFenLeiMapper dianDanFenLeiMapper;

    @Override
    public List<OrderDianDanFenLei> findByShopId(String id) {
        return dianDanFenLeiMapper.findByShopId(id);
    }
}

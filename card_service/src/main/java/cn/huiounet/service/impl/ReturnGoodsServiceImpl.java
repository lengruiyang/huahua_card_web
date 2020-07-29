package cn.huiounet.service.impl;

import cn.huiounet.dao.ReturnGoodsMapper;
import cn.huiounet.pojo.order.ReturnGoods;
import cn.huiounet.service.ReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReturnGoodsServiceImpl implements ReturnGoodsService {
    @Autowired
    private ReturnGoodsMapper returnGoodsMapper;

    @Override
    public List<ReturnGoods> findByOrderNUm(String order_num) {
        return returnGoodsMapper.findByOrderNUm(order_num);
    }

    @Override
    public void saveReturnGoods(ReturnGoods returnGoods) {
        returnGoodsMapper.insert(returnGoods);
    }
}

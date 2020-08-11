package cn.huiounet.service.impl;

import cn.huiounet.dao.PingJiaSysMapper;
import cn.huiounet.pojo.pingjia.PingJiaSys;
import cn.huiounet.service.PingJiaSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PingJiaSysServiceImpl implements PingJiaSysService {
    @Autowired
    private PingJiaSysMapper pingJiaSysMapper;
    @Override
    public List<PingJiaSys> findByUser_id(String user_id, int start, int size) {
        return pingJiaSysMapper.findByUser_id(user_id, start, size);
    }

    @Override
    public List<PingJiaSys> findByOrderNum(String order_num) {
        return pingJiaSysMapper.findByOrderNum(order_num);
    }

    @Override
    public void savePingJia(PingJiaSys pingJiaSys) {
        pingJiaSysMapper.insert(pingJiaSys);
    }

    @Override
    public void deleteById(String id) {
        pingJiaSysMapper.deleteById(id);
    }

    @Override
    public List<PingJiaSys> findByGoodsId(String goods_id, int start, int size) {
        return pingJiaSysMapper.findByGoodsId(goods_id, start, size);
    }
}

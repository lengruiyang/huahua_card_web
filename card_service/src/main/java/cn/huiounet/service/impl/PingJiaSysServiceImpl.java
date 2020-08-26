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
    public int findByNum(String star, String goods_id) {
        return pingJiaSysMapper.findByNum(star,goods_id);
    }

    @Override
    public List<PingJiaSys> GoodsPj(String goods_id,int start,int size) {
        return pingJiaSysMapper.GoodsPj(goods_id, start, size);
    }

    @Override
    public List<PingJiaSys> PoorPj(String goods_id,int start,int size) {
        return pingJiaSysMapper.PoorPj(goods_id, start, size);
    }

    @Override
    public List<PingJiaSys> MPj(String goods_id,int start,int size) {
        return pingJiaSysMapper.MPj(goods_id, start, size);
    }

    @Override
    public List<PingJiaSys> findByImg(String goods_id, int start, int size) {
        return pingJiaSysMapper.findByImg(goods_id, start, size);
    }

    @Override
    public List<PingJiaSys> findByUserGoodsId(String goods_id, String user_id) {
        return pingJiaSysMapper.findByUserGoodsId(goods_id, user_id);
    }

    @Override
    public List<PingJiaSys> findByGoodsId(String goods_id, int start, int size) {
        return pingJiaSysMapper.findByGoodsId(goods_id, start, size);
    }

    @Override
    public PingJiaSys findById(String id) {
        return pingJiaSysMapper.findById(id);
    }
}

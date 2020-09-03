package cn.huiounet.service.impl;

import cn.huiounet.dao.GoodsSysMapper;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.service.GoodsSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSysServiceImpl implements GoodsSysService {
    @Autowired
    private GoodsSysMapper goodsSysMapper;

    @Override
    public GoodsSys findId(String id) {
        return goodsSysMapper.findId(id);
    }

    @Override
    public List<GoodsSys> findBySell(int start, int size) {
        return goodsSysMapper.findBySell(start, size);
    }

    @Override
    public List<GoodsSys> findNewGoods() {
        return goodsSysMapper.findNewGoods();
    }

    @Override
    public List<GoodsSys> findByLike(int start, int size) {
        return goodsSysMapper.findByLike(start, size);
    }

    @Override
    public void updateSell_many(String sell_many, String id) {
        goodsSysMapper.updateSell_many(sell_many, id);
    }

    @Override
    public List<GoodsSys> SearchGoods(String goods_name) {
        return goodsSysMapper.SearchGoods(goods_name);
    }

    @Override
    public List<GoodsSys> findAll(int start, int pageSize) {
        return goodsSysMapper.findAll(start, pageSize);
    }

    @Override
    public List<GoodsSys> findByShop_id(String shop_id) {
        return goodsSysMapper.findByShop_id(shop_id);
    }

    @Override
    public void updateLike(String like_many, String id) {
        goodsSysMapper.updateLike(like_many,id);
    }

    @Override
    public List<GoodsSys> findByShopIdOrderBySellMany(String shop_id, int stsrt, int size) {
        return goodsSysMapper.findByShopIdOrderBySellMany(shop_id, stsrt, size);
    }

    @Override
    public List<GoodsSys> findByShopIdOrderByLike(String shop_id, int stsrt, int size) {
        return goodsSysMapper.findByShopIdOrderByLike(shop_id, stsrt, size);
    }

    @Override
    public List<GoodsSys> findByShopIdOrderByPriceDESC(String shop_id, int stsrt, int size) {
        return goodsSysMapper.findByShopIdOrderByPriceDESC(shop_id, stsrt, size);
    }

    @Override
    public List<GoodsSys> findByShopIdOrderByPriceASC(String shop_id, int stsrt, int size) {
        return goodsSysMapper.findByShopIdOrderByPriceASC(shop_id, stsrt, size);
    }

    @Override
    public List<GoodsSys> findByShopId(String shop_id, int stsrt, int size) {
        return goodsSysMapper.findByShopId(shop_id, stsrt, size);
    }


}

package cn.huiounet.service.impl;

import cn.huiounet.dao.CartSysMapper;
import cn.huiounet.pojo.cart.CartSys;
import cn.huiounet.service.CartSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSysServiceIml implements CartSysService{
    @Autowired
    private CartSysMapper cartSysMapper;

    @Override
    public List<CartSys> findByUser_id(String user_id) {
        return cartSysMapper.findByUser_id(user_id);
    }

    @Override
    public void saveCartSys(CartSys cartSys) {
        cartSysMapper.insert(cartSys);
    }

    @Override
    public void deleteById(String id) {
        cartSysMapper.deleteById(id);
    }

    @Override
    public List<CartSys> GroupBy(String user_id) {
        return cartSysMapper.GroupBy(user_id);
    }

    @Override
    public List<CartSys> findByShop_id(String shop_id) {
        return cartSysMapper.findByShop_id(shop_id);
    }

    @Override
    public CartSys findById(String id) {
        return cartSysMapper.findById(id);
    }
}

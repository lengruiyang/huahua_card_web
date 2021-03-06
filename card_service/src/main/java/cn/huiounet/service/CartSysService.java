package cn.huiounet.service;

import cn.huiounet.pojo.cart.CartSys;

import java.util.List;

public interface CartSysService {

    List<CartSys> findByUser_id(String user_id);

    void saveCartSys(CartSys cartSys);

    void deleteById(String id);

    List<CartSys> GroupBy(String user_id);

    List<CartSys> findByShop_id(String shop_id);

    CartSys findById(String id);
}

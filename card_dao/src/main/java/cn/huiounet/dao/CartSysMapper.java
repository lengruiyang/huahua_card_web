package cn.huiounet.dao;

import cn.huiounet.pojo.cart.CartSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CartSysMapper extends Mapper<CartSys> {

    List<CartSys> findByUser_id(String user_id);

    void deleteById(String id);
}

package cn.huiounet.service;

import cn.huiounet.pojo.shop.AddShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/17 16:21
 * @Version 1.0
 **/
public interface AddShopService {
    AddShop findByUserId(String user_id);

    void updateStatusById(String status,int id);

    void saveAddShop(AddShop addShop);

    List<AddShop> findPage(int start,int size);

    Long AllSize();
}

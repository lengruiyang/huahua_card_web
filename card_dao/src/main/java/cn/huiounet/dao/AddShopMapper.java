package cn.huiounet.dao;

import cn.huiounet.pojo.shop.AddShop;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/17 16:16
 * @Version 1.0
 **/
public interface AddShopMapper extends Mapper<AddShop> {
    AddShop findByUserId(String user_id);

    List<AddShop> findPage(@Param(value = "start")int start,@Param(value = "size")int size);

    void updateStatusById(@Param(value = "status")String status,@Param(value = "id")int id);
}

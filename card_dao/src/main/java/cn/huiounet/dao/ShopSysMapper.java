package cn.huiounet.dao;

import cn.huiounet.pojo.shop.ShopSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopSysMapper extends Mapper<ShopSys> {

    ShopSys findByOpenId(String user_open_id);

    ShopSys findById(String id);

}

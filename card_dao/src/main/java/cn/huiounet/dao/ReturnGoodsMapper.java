package cn.huiounet.dao;

import cn.huiounet.pojo.order.ReturnGoods;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ReturnGoodsMapper extends Mapper<ReturnGoods>{

    List<ReturnGoods> findByOrderNUm(String order_num);

}

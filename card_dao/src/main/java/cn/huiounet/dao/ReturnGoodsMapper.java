package cn.huiounet.dao;

import cn.huiounet.pojo.order.ReturnGoods;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ReturnGoodsMapper extends Mapper<ReturnGoods>{

    List<ReturnGoods> findByOrderNUm(String order_num);

    void updateByOrderNum(@Param(value = "is_pj")String is_pj,@Param(value = "id")String id);

}

package cn.huiounet.dao;

import cn.huiounet.pojo.goods.GoodsSc;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GoodsScMapper extends Mapper<GoodsSc> {

    GoodsSc findSc(@Param(value = "goods_id")String goods_id,@Param(value = "user_id")String user_id);

    void deleteById(@Param(value = "goods_id")String goods_id,@Param(value = "user_id")String user_id);
}

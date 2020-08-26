package cn.huiounet.dao;

import cn.huiounet.pojo.pingjia.PingJiaSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PingJiaSysMapper extends Mapper<PingJiaSys> {

    List<PingJiaSys> findByUser_id(@Param(value = "user_id")String user_id,@Param(value = "start")int start,@Param(value = "size")int size);

    List<PingJiaSys> findByImg(@Param(value = "goods_id")String goods_id,@Param(value = "start")int start,@Param(value = "size")int size);

    List<PingJiaSys> findByOrderNum(String order_num);

    void deleteById(String id);

    PingJiaSys findById(String id);

    int findByNum(@Param(value = "star")String star,@Param(value = "goods_id")String goods_id);

    List<PingJiaSys> GoodsPj(@Param(value = "goods_id")String goods_id, @Param(value = "start")int start,@Param(value = "size")int size);
    List<PingJiaSys> PoorPj( @Param(value = "goods_id")String goods_id,@Param(value = "start")int start,@Param(value = "size")int size);
    List<PingJiaSys> MPj(@Param(value = "goods_id")String goods_id,@Param(value = "start")int start,@Param(value = "size")int size);

    List<PingJiaSys> findByUserGoodsId(@Param(value = "goods_id") String goods_id,@Param(value = "user_id")String user_id);

    List<PingJiaSys> findByGoodsId(@Param(value = "goods_id") String goods_id,@Param(value = "start")int start,@Param(value = "size")int size);
}

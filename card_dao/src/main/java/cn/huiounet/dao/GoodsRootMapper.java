package cn.huiounet.dao;

import cn.huiounet.pojo.root.GoodsRoot;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsRootMapper extends Mapper<GoodsRoot> {

    List<GoodsRoot> findByUserId(@Param(value = "user_id")String user_id,@Param(value = "start")int start,@Param(value = "size")int size);

}

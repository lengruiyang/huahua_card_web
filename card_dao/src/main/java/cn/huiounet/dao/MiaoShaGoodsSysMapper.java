package cn.huiounet.dao;

import cn.huiounet.pojo.miaosha.MiaoShaGoodsSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MiaoShaGoodsSysMapper extends Mapper<MiaoShaGoodsSys> {

    void updateStartTime(@Param(value = "start_time")String start_time,@Param(value = "id")String id);
}

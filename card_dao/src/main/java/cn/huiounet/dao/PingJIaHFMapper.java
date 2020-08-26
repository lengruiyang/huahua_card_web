package cn.huiounet.dao;

import cn.huiounet.pojo.pingjia.PingJIaHF;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PingJIaHFMapper extends Mapper<PingJIaHF> {

    List<PingJIaHF> findByPjId(@Param(value = "pj_id") String pj_id,@Param(value = "start")int start,@Param(value = "size")int size);
}

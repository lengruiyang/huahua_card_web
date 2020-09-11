package cn.huiounet.dao;

import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YuYueMiaoShaMapper extends Mapper<YuYueMiaoSha> {

    List<YuYueMiaoSha> findByStartTime(String start_time);

    YuYueMiaoSha findByUserIdAndStartTime(@Param(value = "user_id") String user_id,@Param(value = "start_time") String start_time);

    void deleteByUserIdAndStartTime(@Param(value = "user_id") String user_id,@Param(value = "start_time")String start_time);

}

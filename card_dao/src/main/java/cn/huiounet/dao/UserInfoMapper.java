package cn.huiounet.dao;

import cn.huiounet.pojo.UserInfoSystem;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserInfoMapper extends Mapper<UserInfoSystem>{

    UserInfoSystem findByOpenId(String open_id);
    UserInfoSystem findById(String id);
    UserInfoSystem findByPhone(String phone);
    void updateQM(@Param(value = "qian_ming")String qian_ming,@Param(value = "id")String id);
}

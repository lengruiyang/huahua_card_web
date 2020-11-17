package cn.huiounet.dao;

import cn.huiounet.pojo.UserSys;
import tk.mybatis.mapper.common.Mapper;

public interface UserSysMapper extends Mapper<UserSys> {

    UserSys findByUserName(String user_name);
}

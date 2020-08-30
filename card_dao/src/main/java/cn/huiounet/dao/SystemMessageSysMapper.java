package cn.huiounet.dao;

import cn.huiounet.pojo.message.SystemMessageSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SystemMessageSysMapper extends Mapper<SystemMessageSys> {

    List<SystemMessageSys> findByStatus();

}

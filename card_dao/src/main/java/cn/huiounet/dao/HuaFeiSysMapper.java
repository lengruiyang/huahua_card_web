package cn.huiounet.dao;

import cn.huiounet.pojo.huafei.HuaFeiSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HuaFeiSysMapper extends Mapper<HuaFeiSys>{

    List<HuaFeiSys> findAll();

    HuaFeiSys findById(String id);

}

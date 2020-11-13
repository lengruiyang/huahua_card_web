package cn.huiounet.dao;

import cn.huiounet.pojo.pintuan.PingTuanSys;
import tk.mybatis.mapper.common.Mapper;

public interface PingTuanSysMapper extends Mapper<PingTuanSys> {

    PingTuanSys findById(String id);
}

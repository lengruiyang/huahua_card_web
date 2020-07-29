package cn.huiounet.dao;

import cn.huiounet.pojo.daohang.DaoHangSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DaoHangSysMapper extends Mapper<DaoHangSys>{

    List<DaoHangSys> findByStatus();
}

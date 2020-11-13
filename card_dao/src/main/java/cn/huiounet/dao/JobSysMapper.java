package cn.huiounet.dao;

import cn.huiounet.pojo.renwujifen.JobSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface JobSysMapper extends Mapper<JobSys> {

    List<JobSys> findAll();

}

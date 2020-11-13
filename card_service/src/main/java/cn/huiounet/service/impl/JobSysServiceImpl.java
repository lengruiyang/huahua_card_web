package cn.huiounet.service.impl;

import cn.huiounet.dao.JobSysMapper;
import cn.huiounet.pojo.renwujifen.JobSys;
import cn.huiounet.service.JobSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSysServiceImpl implements JobSysService {
    @Autowired
    private JobSysMapper jobSysMapper;

    @Override
    public List<JobSys> findAll() {
        return jobSysMapper.findAll();
    }
}

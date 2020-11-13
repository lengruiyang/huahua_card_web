package cn.huiounet.dao;

import cn.huiounet.pojo.renwujifen.JiFenGoodsFenLeiSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface JiFenGoodsFenLeiSysMapper extends Mapper<JiFenGoodsFenLeiSys> {

    List<JiFenGoodsFenLeiSys> findAllStatus();

}

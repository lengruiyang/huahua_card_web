package cn.huiounet.dao;

import cn.huiounet.pojo.fenlei.FenLei_Farther_sys;
import tk.mybatis.mapper.common.Mapper;

public interface FenLei_Farther_sysMapper extends Mapper<FenLei_Farther_sys>{

    FenLei_Farther_sys findById(String id);
}

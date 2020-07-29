package cn.huiounet.dao;

import cn.huiounet.pojo.fenlei.FenLei_Son_sys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FenLei_Son_sysMapper extends Mapper<FenLei_Son_sys>{

    List<FenLei_Son_sys> findByFartherId(String farther_id);
}

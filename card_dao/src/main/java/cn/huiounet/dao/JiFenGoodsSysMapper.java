package cn.huiounet.dao;

import cn.huiounet.pojo.renwujifen.JiFenGoodsSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface JiFenGoodsSysMapper extends Mapper<JiFenGoodsSys> {

    List<JiFenGoodsSys> findByFenLei(@Param(value = "jifen_fen_lei") String jifen_fen_lei,@Param(value = "start")int start,@Param(value = "size")int size);

    JiFenGoodsSys findById(String id);



}

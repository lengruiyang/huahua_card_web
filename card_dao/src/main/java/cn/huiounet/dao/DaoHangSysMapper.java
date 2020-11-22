package cn.huiounet.dao;

import cn.huiounet.pojo.daohang.DaoHangSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DaoHangSysMapper extends Mapper<DaoHangSys>{

    List<DaoHangSys> findByStatus();

    void updateById(@Param(value = "status")String status,@Param(value = "id")int id);

    void deleteById(int id);

    DaoHangSys findById(int id);

    void updateMessById(@Param(value = "name")String name,@Param(value = "img")String img,@Param(value = "to_url")String to_url,@Param(value = "id")int id);
}

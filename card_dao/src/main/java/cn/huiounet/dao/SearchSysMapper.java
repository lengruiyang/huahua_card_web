package cn.huiounet.dao;

import cn.huiounet.pojo.search.SearchSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SearchSysMapper extends Mapper<SearchSys>{

    List<SearchSys> findByUser(String user_id);

    void deleteByUser(String user_id);

    SearchSys findByUserAndMess(@Param(value = "user_id")String user_id,@Param(value = "search_mess")String search_mess);
}

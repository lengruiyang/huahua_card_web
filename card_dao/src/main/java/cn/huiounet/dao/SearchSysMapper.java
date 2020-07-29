package cn.huiounet.dao;

import cn.huiounet.pojo.search.SearchSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SearchSysMapper extends Mapper<SearchSys>{

    List<SearchSys> findByUser(String user_id);
}

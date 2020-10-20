package cn.huiounet.dao;

import cn.huiounet.pojo.search.SearchCount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SearchCountMapper extends Mapper<SearchCount> {

    SearchCount findBySearchMess(String search_mess);

    List<SearchCount> findBySearchOrderBy(@Param(value = "start")int start,@Param(value = "size")int size);

    void updateBySerach(@Param(value = "count")int count,@Param(value = "search_mess")String search_mess);

    List<SearchCount> searchLike(@Param(value = "search_mess")String search_mess,@Param(value = "start")int start,@Param(value = "size")int size);
}

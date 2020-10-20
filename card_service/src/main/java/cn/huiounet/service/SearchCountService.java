package cn.huiounet.service;

import cn.huiounet.pojo.search.SearchCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchCountService {

    SearchCount findBySearchMess(String search_mess);

    List<SearchCount> findBySearchOrderBy(int start,int size);

    void saveSearchMess(SearchCount searchCount);

    void updateBySerach(int count,String search_mess);

    List<SearchCount> searchLike(String search_mess,int start,int size);

}

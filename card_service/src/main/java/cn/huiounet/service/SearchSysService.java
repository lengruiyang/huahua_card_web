package cn.huiounet.service;

import cn.huiounet.pojo.search.SearchSys;

import java.util.List;

public interface SearchSysService {

    List<SearchSys> findByUser(String user_id);

    void saveSerarch(SearchSys searchSys);

    void deleteByUser(String user_id);

    SearchSys findByUserAndMess(String user_id,String search_mess);
}

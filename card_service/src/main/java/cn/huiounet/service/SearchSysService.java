package cn.huiounet.service;

import cn.huiounet.pojo.search.SearchSys;

import java.util.List;

public interface SearchSysService {

    List<SearchSys> findByUser(String user_id);

    void saveSerarch(SearchSys searchSys);
}

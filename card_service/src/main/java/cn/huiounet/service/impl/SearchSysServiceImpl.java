package cn.huiounet.service.impl;

import cn.huiounet.dao.SearchSysMapper;
import cn.huiounet.pojo.search.SearchSys;
import cn.huiounet.service.SearchSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchSysServiceImpl implements SearchSysService{
    @Autowired
    private SearchSysMapper searchSysMapper;

    @Override
    public List<SearchSys> findByUser(String user_id) {
        return searchSysMapper.findByUser(user_id);
    }

    @Override
    public void saveSerarch(SearchSys searchSys) {
        searchSysMapper.insert(searchSys);
    }

    @Override
    public void deleteByUser(String user_id) {
        searchSysMapper.deleteByUser(user_id);
    }

    @Override
    public SearchSys findByUserAndMess(String user_id, String search_mess) {
        return searchSysMapper.findByUserAndMess(user_id, search_mess);
    }

}

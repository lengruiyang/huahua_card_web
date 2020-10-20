package cn.huiounet.service.impl;

import cn.huiounet.dao.SearchCountMapper;
import cn.huiounet.pojo.search.SearchCount;
import cn.huiounet.service.SearchCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCountServiceImpl implements SearchCountService {
    @Autowired
    private SearchCountMapper searchCountMapper;

    @Override
    public SearchCount findBySearchMess(String search_mess) {
        return searchCountMapper.findBySearchMess(search_mess);
    }

    @Override
    public List<SearchCount> findBySearchOrderBy(int start, int size) {
        return searchCountMapper.findBySearchOrderBy(start, size);
    }

    @Override
    public void saveSearchMess(SearchCount searchCount) {
        searchCountMapper.insert(searchCount);
    }

    @Override
    public void updateBySerach(int count, String search_mess) {
        searchCountMapper.updateBySerach(count, search_mess);
    }

    @Override
    public List<SearchCount> searchLike(String search_mess, int start, int size) {
        return searchCountMapper.searchLike(search_mess, start, size);
    }
}

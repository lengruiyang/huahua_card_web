package cn.huiounet.service.impl;

import cn.huiounet.dao.videoSysMapper;
import cn.huiounet.pojo.video.VideoCardSys;
import cn.huiounet.service.VideoCardSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoCardSysServiceImpl implements VideoCardSysService {
    @Autowired
    private videoSysMapper mapper;
    @Override
    public List<VideoCardSys> findByGoodsId(String goods_id) {
        return mapper.findByGoodsId(goods_id);
    }
}

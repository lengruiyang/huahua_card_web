package cn.huiounet.service;

import cn.huiounet.pojo.video.VideoCardSys;

import java.util.List;

public interface VideoCardSysService {

    List<VideoCardSys> findByGoodsId(String goods_id);

}

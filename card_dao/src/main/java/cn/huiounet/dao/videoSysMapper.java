package cn.huiounet.dao;

import cn.huiounet.pojo.video.VideoCardSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface videoSysMapper extends Mapper<VideoCardSys> {

    List<VideoCardSys> findByGoodsId(String goods_id);

}

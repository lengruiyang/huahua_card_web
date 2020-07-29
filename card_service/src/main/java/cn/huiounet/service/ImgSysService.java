package cn.huiounet.service;

import cn.huiounet.pojo.img.ImgSys;

import java.util.List;

public interface ImgSysService {

    List<ImgSys> findByGoodsId(String goods_id);

    List<ImgSys> findByLunBo();

}

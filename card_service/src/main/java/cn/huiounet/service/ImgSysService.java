package cn.huiounet.service;

import cn.huiounet.pojo.img.ImgSys;

import java.util.List;

public interface ImgSysService {

    List<ImgSys> findByGoodsId(String goods_id);

    List<ImgSys> findByLunBo();

    List<ImgSys> findByFenLeiLunBo();

    void saveImg(ImgSys imgSys);

    List<ImgSys> findByFenLeiLunBoShouYe();

    List<ImgSys> findByFenLeiLunBoBystatus(String status);

}

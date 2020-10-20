package cn.huiounet.dao;

import cn.huiounet.pojo.img.ImgSys;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImgSysMapper extends Mapper<ImgSys> {

    List<ImgSys> findByGoodsId(String goods_id);

    List<ImgSys> findByLunBo();

    List<ImgSys> findByFenLeiLunBo();

    List<ImgSys> findByFenLeiLunBoShouYe();

    List<ImgSys> findByFenLeiLunBoBystatus(String status);
}

package cn.huiounet.dao;

import cn.huiounet.pojo.img.ImgSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImgSysMapper extends Mapper<ImgSys> {

    List<ImgSys> findByGoodsId(String goods_id);

    List<ImgSys> findByLunBo();

    List<ImgSys> findByFenLeiLunBo();

    List<ImgSys> findByFenLeiLunBoShouYe();

    List<ImgSys> findByFenLeiLunBoBystatus(String status);

    void deleteById(int id);

    void updateById(@Param(value = "url")String url,@Param(value = "to_url")String to_url,@Param(value = "goods_id")String goods_id,@Param(value = "id")int id);
}

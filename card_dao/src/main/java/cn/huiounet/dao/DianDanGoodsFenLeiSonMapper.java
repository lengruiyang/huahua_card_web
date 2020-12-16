package cn.huiounet.dao;

import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiSon;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DianDanGoodsFenLeiSonMapper extends Mapper<DianDanGoodsFenLeiSon> {

    List<DianDanGoodsFenLeiSon> findByFatherId(String farther_id);
}

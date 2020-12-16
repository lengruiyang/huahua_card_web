package cn.huiounet.service;

import cn.huiounet.pojo.diandan.DianDanGoodsFenLeiSon;

import java.util.List;

public interface DianDanGoodsFenLeiSonService {

    List<DianDanGoodsFenLeiSon> findByFatherId(String farther_id);
}

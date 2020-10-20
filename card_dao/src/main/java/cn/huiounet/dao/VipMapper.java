package cn.huiounet.dao;

import cn.huiounet.pojo.vip.Vip;
import tk.mybatis.mapper.common.Mapper;

public interface VipMapper  extends Mapper<Vip>{

    Vip findById();
}

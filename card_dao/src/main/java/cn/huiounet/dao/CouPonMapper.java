package cn.huiounet.dao;

import cn.huiounet.pojo.coupon.CouPon;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouPonMapper extends Mapper<CouPon> {

    List<CouPon> findBySys();

    List<CouPon> findBySysGoods(String goods_id);

    List<CouPon> findBySysShop(String shop_id);

    List<CouPon> findByVipSys();

    void updateNum(@Param(value = "num") String num, @Param(value = "id") String id);

    CouPon findById(String id);
}

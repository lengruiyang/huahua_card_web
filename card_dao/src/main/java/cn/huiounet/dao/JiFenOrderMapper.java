package cn.huiounet.dao;

import cn.huiounet.pojo.renwujifen.JiFenOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface JiFenOrderMapper extends Mapper<JiFenOrder> {

    JiFenOrder findByOrderNum(String order_num);

    void updateByOrderNum(@Param(value = "is_change")String is_change,@Param(value = "change_time")String change_time,@Param(value = "order_num")String order_num);

    void updateAddressByOrderNum(@Param(value = "address_id")String address_id,@Param(value = "order_num")String order_num);

}

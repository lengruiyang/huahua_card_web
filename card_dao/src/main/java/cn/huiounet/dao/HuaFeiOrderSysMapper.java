package cn.huiounet.dao;

import cn.huiounet.pojo.huafei.HuaFeiOrderSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HuaFeiOrderSysMapper extends Mapper<HuaFeiOrderSys> {

    HuaFeiOrderSys findByOrderNum(String order_num);

    void updateByOrderNum(@Param(value = "pay_status") String pay_status, @Param(value = "pay_time") String pay_time, @Param(value = "order_num") String order_num);

    List<HuaFeiOrderSys> findByUserId(@Param(value = "user_id") String user_id, @Param(value = "start") int start, @Param(value = "size") int size);

    List<HuaFeiOrderSys> findPayButNotSuccess(String is_cz_success);

    void deleteById(String id);

    void updateById(@Param(value = "is_cz_success") String is_cz_success, @Param(value = "id") String id);

}

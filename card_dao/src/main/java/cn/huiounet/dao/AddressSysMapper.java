package cn.huiounet.dao;

import cn.huiounet.pojo.address.AddressSys;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AddressSysMapper extends Mapper<AddressSys>{

    List<AddressSys> findByUser_id(String user_id);

    void deleteById(String id);

    AddressSys findById(String id);

    AddressSys findByStatus(@Param(value = "user_id")String user_id,@Param(value = "status")String status);

    void updateStatus(@Param(value = "status")String status,@Param(value = "id")String id);
}

package cn.huiounet.dao;

import cn.huiounet.pojo.UserInfoSystem;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserInfoMapper extends Mapper<UserInfoSystem> {

    UserInfoSystem findByOpenId(String open_id);

    UserInfoSystem findById(String id);

    UserInfoSystem findByPhone(String phone);

    void updatePassWord(@Param(value = "password") String password, @Param(value = "id") String id);

    void updateMoney(@Param(value = "money") String money, @Param(value = "id") String id);

    void updateQM(@Param(value = "qian_ming") String qian_ming, @Param(value = "id") String id);

    void updateVipTime(@Param(value = "vip_time") String vip_time, @Param(value = "id") String id);
}

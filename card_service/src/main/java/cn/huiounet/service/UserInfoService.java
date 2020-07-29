package cn.huiounet.service;

import cn.huiounet.pojo.UserInfoSystem;

import java.util.List;

public interface UserInfoService {


    List<UserInfoSystem> findAll();

    UserInfoSystem findByOpenId(String open_id);

    void saveUser(UserInfoSystem userInfoSystem);

    UserInfoSystem findByPhone(String phone);

    void updateQM(String qian_ming,String id);

    UserInfoSystem findById(String id);
}

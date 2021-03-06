package cn.huiounet.service;

import cn.huiounet.pojo.UserInfoSystem;

import java.util.List;

public interface UserInfoService {

    List<UserInfoSystem> findBySex(String sex);

    List<UserInfoSystem> findAll();

    List<UserInfoSystem> findAllUser(int start,int size);

    UserInfoSystem findByOpenId(String open_id);

    void saveUser(UserInfoSystem userInfoSystem);

    UserInfoSystem findByPhone(String phone);

    void updateQM(String qian_ming, String id);

    void updateMoney(String money, String id);

    UserInfoSystem findById(String id);

    void updatePassWord(String password, String id);

    void updateVipTime(String vip_time,String id);

    void updateJiFen(String jifen, String id);
}

package cn.huiounet.service.impl;

import cn.huiounet.dao.UserInfoMapper;
import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfoSystem> findAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public UserInfoSystem findByOpenId(String open_id) {
        return userInfoMapper.findByOpenId(open_id);
    }

    @Override
    public void saveUser(UserInfoSystem userInfoSystem) {
        userInfoMapper.insert(userInfoSystem);
    }

    @Override
    public UserInfoSystem findByPhone(String phone) {
        return userInfoMapper.findByPhone(phone);
    }

    @Override
    public void updateQM(String qian_ming, String id) {
        userInfoMapper.updateQM(qian_ming, id);
    }

    @Override
    public UserInfoSystem findById(String id) {
        return userInfoMapper.findById(id);
    }
}

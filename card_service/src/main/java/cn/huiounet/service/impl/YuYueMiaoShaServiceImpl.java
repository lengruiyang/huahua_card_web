package cn.huiounet.service.impl;

import cn.huiounet.dao.YuYueMiaoShaMapper;
import cn.huiounet.pojo.miaosha.YuYueMiaoSha;
import cn.huiounet.service.YuYueMiaoShaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YuYueMiaoShaServiceImpl implements YuYueMiaoShaService {
    @Autowired
    private YuYueMiaoShaMapper yuYueMiaoShaMapper;

    @Override
    public List<YuYueMiaoSha> findByStartTime(String start_time) {
        return yuYueMiaoShaMapper.findByStartTime(start_time);
    }

    @Override
    public void saveYuYue(YuYueMiaoSha yuYueMiaoSha) {
        yuYueMiaoShaMapper.insert(yuYueMiaoSha);
    }

    @Override
    public YuYueMiaoSha findByUserIdAndStartTime(String user_id, String start_time) {
        return yuYueMiaoShaMapper.findByUserIdAndStartTime(user_id,start_time);
    }

    @Override
    public void deleteByUserIdAndStartTime(String user_id, String start_time) {
        yuYueMiaoShaMapper.deleteByUserIdAndStartTime(user_id, start_time);
    }
}

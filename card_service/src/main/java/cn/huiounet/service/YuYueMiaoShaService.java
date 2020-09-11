package cn.huiounet.service;

import cn.huiounet.pojo.miaosha.YuYueMiaoSha;

import java.util.List;

public interface YuYueMiaoShaService {

    List<YuYueMiaoSha> findByStartTime(String start_time);

    void saveYuYue(YuYueMiaoSha yuYueMiaoSha);

    YuYueMiaoSha findByUserIdAndStartTime(String user_id,String start_time);

    void deleteByUserIdAndStartTime(String user_id,String start_time);
}

package cn.huiounet.service.impl;

import cn.huiounet.dao.PayLogNoticPojoMapper;
import cn.huiounet.pojo.notic.PayLogNoticPojo;
import cn.huiounet.service.PayLogNoticPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/26 15:01
 * @Version 1.0
 **/
@Service
public class PayLogNoticPojoServiceImpl implements PayLogNoticPojoService {
    @Autowired
    private PayLogNoticPojoMapper payLogNoticPojoMapper;

    @Override
    public List<PayLogNoticPojo> findByUserId(String user_id, String day_time) {

        return payLogNoticPojoMapper.findByUserId(user_id, day_time);
    }

    @Override
    public List<String> findMonth(String user_id) {
        return payLogNoticPojoMapper.findMonth(user_id);
    }

    @Override
    public void saveLog(PayLogNoticPojo payLogNoticPojo) {
        payLogNoticPojoMapper.insert(payLogNoticPojo);
    }
}

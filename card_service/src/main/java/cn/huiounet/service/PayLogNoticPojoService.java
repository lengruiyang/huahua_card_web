package cn.huiounet.service;

import cn.huiounet.pojo.notic.PayLogNoticPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/26 15:01
 * @Version 1.0
 **/
public interface PayLogNoticPojoService {

    List<PayLogNoticPojo> findByUserId(String user_id,String day_time);

    List<String> findMonth(String user_id);

    void saveLog(PayLogNoticPojo payLogNoticPojo);
}

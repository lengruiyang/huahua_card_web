package cn.huiounet.dao;

import cn.huiounet.pojo.notic.PayLogNoticPojo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/26 11:16
 * @Version 1.0
 **/
public interface PayLogNoticPojoMapper extends Mapper<PayLogNoticPojo> {

    List<PayLogNoticPojo> findByUserId(@Param(value = "user_id")String user_id,@Param(value = "day_time")String day_time);

    List<String> findMonth(String user_id);

}

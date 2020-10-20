package cn.huiounet.service;

import cn.huiounet.pojo.vip.VipLog;

public interface VipLogService {

    void updateByOrderNum(String status,String order_num);

    void saveVipLog(VipLog vipLog);

    VipLog findByOrderNum(String order_num);
}

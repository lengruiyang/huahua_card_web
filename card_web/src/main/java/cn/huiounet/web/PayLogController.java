package cn.huiounet.web;

import cn.huiounet.pojo.notic.PayLogNoticPojo;
import cn.huiounet.pojo.notic.ReturnPayLog;
import cn.huiounet.service.PayLogNoticPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Code By 冷瑞阳
 * @Date 2020/12/27 19:16
 * @Version 1.0
 **/
@RestController
@RequestMapping("payLog")
public class PayLogController {
    @Autowired
    private PayLogNoticPojoService payLogNoticPojoService;

    @GetMapping("log")
    public List<ReturnPayLog> getLogs(String user_id){
        List<String> month = payLogNoticPojoService.findMonth(user_id);
        List<ReturnPayLog> returnPayLogs = new ArrayList<>();
        for(int i = 0;i<month.size();i++){
            ReturnPayLog returnPayLog = new ReturnPayLog();
            String s = month.get(i);
            int money = 0;
            List<PayLogNoticPojo> byUserId = payLogNoticPojoService.findByUserId(user_id, s);
            for(int n = 0;n<byUserId.size();n++){
                PayLogNoticPojo payLogNoticPojo = byUserId.get(n);
                String pay_num = payLogNoticPojo.getPay_num();
                int i1 = Integer.parseInt(pay_num);
                money += i1;
            }
            returnPayLog.setLogNoticPojos(byUserId);
            returnPayLog.setMoney((float) money);
            returnPayLog.setMonth(s);
            returnPayLogs.add(returnPayLog);
        }
        return returnPayLogs;
    }
}

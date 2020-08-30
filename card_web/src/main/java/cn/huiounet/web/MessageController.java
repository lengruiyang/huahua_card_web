package cn.huiounet.web;

import cn.huiounet.pojo.message.SystemMessageSys;
import cn.huiounet.service.SystemMessageSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private SystemMessageSysService systemMessageSysService;

    @GetMapping("/system")
    public List<SystemMessageSys> findAll(){

        List<SystemMessageSys> byStatus = systemMessageSysService.findByStatus();

        return byStatus;
    }
}

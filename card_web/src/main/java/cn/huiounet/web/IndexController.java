package cn.huiounet.web;

import cn.huiounet.pojo.daohang.DaoHangSys;
import cn.huiounet.service.DaoHangSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private DaoHangSysService daoHangSysService;

    /**
     * system
     * @return
     */
    @GetMapping("/dao_hang")
    private List<DaoHangSys> findAllDaoHang(){

        List<DaoHangSys> daoHangSys = daoHangSysService.findByStatus();

        return daoHangSys;
    }
}

package cn.huiounet.web;

import cn.huiounet.pojo.daohang.DaoHangSys;
import cn.huiounet.pojo.vo.Result;
import cn.huiounet.service.DaoHangSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/update_dao_hang")
    private void updateStatus(int id){
        DaoHangSys byId = daoHangSysService.findById(id);
        String status = byId.getStatus();
        if(status.equals("0")){
            daoHangSysService.updateById("1",id);
        }else {
            daoHangSysService.updateById("0",id);
        }
    }

    @GetMapping("/update_dao_hang_mess")
    private void update_dao_hang_mess(String name,String url,String to_url,int id){
        String urlLast = "";
        if (to_url.equals("")){
            urlLast = null;
        }else {
            urlLast = to_url;
        }

        daoHangSysService.updateMessById(name,url,urlLast,id);
    }

    @GetMapping("/delete_dao_hang")
    private void delete_dao_hang(int id){

        daoHangSysService.deleteById(id);

    }

    @GetMapping("/find_dao_hang")
    private DaoHangSys find_dao_hang(int id){

        DaoHangSys byId = daoHangSysService.findById(id);

        return byId;

    }

    @GetMapping("/save_dao_hang")
    private Result save_dao_hang(String name,String img,String to_url){

        DaoHangSys daoHangSys = new DaoHangSys();
        daoHangSys.setImg(img);
        daoHangSys.setName(name);
        if (to_url.equals("")){
            daoHangSys.setTo_url(null);
        }else {
            daoHangSys.setTo_url(to_url);
        }
        daoHangSys.setStatus("1");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        daoHangSys.setCreate_time(df.format(new Date()));

        System.out.println(daoHangSys);
        daoHangSysService.save(daoHangSys);
        return Result.ok("ok");
    }


    /**
     * system
     * @return
     */
    @GetMapping("/sysDH")
    private Map findAllDaoHangSys(){

        List<DaoHangSys> daoHangSys = daoHangSysService.findAll();

        Map map = new HashMap();
        map.put("count",daoHangSys.size());
        map.put("code",0);
        map.put("data",daoHangSys);

        return map;
    }
}

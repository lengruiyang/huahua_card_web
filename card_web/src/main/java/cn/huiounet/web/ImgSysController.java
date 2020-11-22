package cn.huiounet.web;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.goods.GoodsSys;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.video.VideoCardSys;
import cn.huiounet.service.GoodsSysService;
import cn.huiounet.service.ImgSysService;
import cn.huiounet.service.UserInfoService;
import cn.huiounet.service.VideoCardSysService;
import cn.huiounet.utils.qrcode.ImageQrcode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/img")
public class ImgSysController {
    private static Logger logger = Logger.getLogger(ImgSysController.class);
    @Autowired
    private ImgSysService imgSysService;

    @Autowired
    private GoodsSysService goodsSysService;

    @Autowired
    private VideoCardSysService videoCardSysService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 系统
     * @return
     */
    @GetMapping("/lun_boSys")
    public Map getLun_boSys(){

        List<ImgSys> byLunBo = imgSysService.findByLunBo();
        Map map = new HashMap();
        map.put("count",byLunBo.size());
        map.put("code",0);
        map.put("data",byLunBo);
        return map;
    }

    @GetMapping("/delete_lun_boSys")
    public void delete_lun_boSys(int id){

        imgSysService.deleteById(id);

    }

    @GetMapping("/save_lun_boSys")
    public void save_lun_boSys(String url,String to_url,String goods_id){

        ImgSys imgSys = new ImgSys();
        imgSys.setUrl(url);
        imgSys.setStatus("2");
        if(to_url.equals("")){
            imgSys.setTo_url(null);
            imgSys.setGoods_id(goods_id);
        }else {
            imgSys.setTo_url(to_url);
            imgSys.setGoods_id(null);
        }
        imgSysService.saveImg(imgSys);
    }

    @GetMapping("/update_lun_boSys")
    public void delete_lun_boSys(int id,String imgurl,String to_url,String goods_id){
        if(to_url.equals("")){
            imgSysService.updateById(imgurl,null,goods_id,id);
        }else {
            imgSysService.updateById(imgurl,to_url,null,id);
        }
    }
    /**
     * 结束
     * @return
     */
    @GetMapping("/lun_bo")
    public List<ImgSys> getLun_bo(){

        List<ImgSys> byLunBo = imgSysService.findByLunBo();

        return byLunBo;
    }

    @GetMapping("/goods_img")
    public List<ImgSys> goods_img(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        List<ImgSys> byGoodsId = imgSysService.findByGoodsId(goods_id);

        return byGoodsId;
    }

    @GetMapping("/fenlei_img")
    public List<ImgSys> fenlei_img(){

        List<ImgSys> byGoodsId = imgSysService.findByFenLeiLunBo();

        return byGoodsId;
    }

    @GetMapping("/status")
    public List<ImgSys> findByStatus(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String status = request.getParameter("status");
        List<ImgSys> byFenLeiLunBoBystatus = imgSysService.findByFenLeiLunBoBystatus(status);

        return byFenLeiLunBoBystatus;
    }


    @GetMapping("/video")
    public List<VideoCardSys> video(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String goods_id = request.getParameter("goods_id");

        List<VideoCardSys> byGoodsId = videoCardSysService.findByGoodsId(goods_id);

        return byGoodsId;
    }

    @GetMapping("/GoodsShareCode")
    public String GoodsSharecode (HttpServletResponse response, HttpServletRequest request)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String goodsId = request.getParameter("goodsId");
        String userid = request.getParameter("userId");

        UserInfoSystem byId = userInfoService.findById(userid);
        GoodsSys id = goodsSysService.findId(goodsId);
        ImageQrcode imageQrcode  = new ImageQrcode();
        logger.info(goodsId);
        logger.info(userid);
        HelpPayController helpPayController = new HelpPayController();
        String qrCode = helpPayController.getQrCode("2|" + goodsId);
        String shopImg = imageQrcode.getShopImg(id.getGoods_name(), id.getSecond_name(), id.getSecond_name(), id.getHight_price(), id.getLow_price(), id.getSc_price(), id.getGoods_cen_img(), qrCode);
        return shopImg;
    }
}

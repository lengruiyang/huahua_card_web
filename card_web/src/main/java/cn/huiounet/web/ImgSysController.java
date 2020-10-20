package cn.huiounet.web;

import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.pojo.video.VideoCardSys;
import cn.huiounet.service.ImgSysService;
import cn.huiounet.service.VideoCardSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/img")
public class ImgSysController {
    @Autowired
    private ImgSysService imgSysService;

    @Autowired
    private VideoCardSysService videoCardSysService;

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
}

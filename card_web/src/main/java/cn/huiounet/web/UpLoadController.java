package cn.huiounet.web;

import cn.huiounet.pojo.img.Image_sys;
import cn.huiounet.utils.qrcode.ImageRemarkUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/upload")
public class UpLoadController {
    private Logger logger = Logger.getLogger(UpLoadController.class);

    @RequestMapping("/img")
    public Image_sys upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        request.setCharacterEncoding("UTF-8");
        logger.info("执行图片上传");
        Image_sys image_sys = new Image_sys();

        String trueFileName="";

        if(!file.isEmpty()) {
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {

                    String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    trueFileName =  format+fileName.substring(0,5)+"."+type;
                    path = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/" + trueFileName;

                    file.transferTo(new File(path));
            }else {
                logger.info("文件类型为空");
                image_sys.setName("error");
                image_sys.setUrl("IsNull");
                return image_sys;
            }
        }else {
            logger.info("文件类型为空");
            image_sys.setName("error");
            image_sys.setUrl("NotFind");
            return image_sys;
        }

        image_sys.setName(trueFileName);
            image_sys.setUrl("https://xcx2.huiounet.cn/imgs/"+trueFileName);


        return image_sys;
    }



    @RequestMapping("/imgsys")
    public Image_sys uploadSys(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        request.setCharacterEncoding("UTF-8");
        logger.info("执行图片上传");
        Image_sys image_sys = new Image_sys();
        String status = request.getParameter("status");
        String sy = request.getParameter("sy");

        String trueFileName="";
        String name = "";
        if(!file.isEmpty()) {
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                trueFileName =  format+fileName.substring(0,5)+"."+type;
                name = "sy" + format+fileName.substring(0,5)+"."+type;
                path = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/" + trueFileName;
                file.transferTo(new File(path));
                if(status.equals("1")) {
                    String path2 = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/" + name;
                    ImageRemarkUtil.SY(path, path2, sy);
                }
            }else {
                logger.info("文件类型为空");
                image_sys.setName("error");
                image_sys.setUrl("IsNull");
                return image_sys;
            }
        }else {
            logger.info("文件类型为空");
            image_sys.setName("error");
            image_sys.setUrl("NotFind");
            return image_sys;
        }

        image_sys.setName(trueFileName);
        if(status.equals("1")){
            image_sys.setUrl("https://xcx2.huiounet.cn/imgs/"+name);
        }else {
            image_sys.setUrl("https://xcx2.huiounet.cn/imgs/"+trueFileName);
        }
        return image_sys;
    }
}

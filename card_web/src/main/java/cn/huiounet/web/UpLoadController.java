package cn.huiounet.web;

import cn.huiounet.pojo.img.Image_sys;
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
        String user = request.getParameter("user");
        logger.info("user:"+user);
        String trueFileName="";
        if(!file.isEmpty()) {
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("MP4".equals(type.toUpperCase())||"jpg".equals(type.toUpperCase())||"png".equals(type.toUpperCase())||"jepg".equals(type.toUpperCase())||"mp4".equals(type.toUpperCase())||"GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
//                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    trueFileName =  format+fileName.substring(0,5)+"."+type;
                    // 设置存放图片文件的路径
//                    path = "/Users/a/Desktop/upload/" + trueFileName;
                    path = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/" + trueFileName;
                    logger.info("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));
                    logger.info("文件成功上传到指定目录下");
                }else {
                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    image_sys.setName("error");
                    image_sys.setUrl("not_we_want");
                    return image_sys;
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
        logger.info("文件类型为空");
        image_sys.setName(trueFileName);
        image_sys.setUrl("https://xcx2.huiounet.cn/imgs/"+trueFileName);
        return image_sys;
    }
}

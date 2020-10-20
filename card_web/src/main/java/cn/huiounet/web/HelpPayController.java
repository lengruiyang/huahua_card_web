package cn.huiounet.web;

import cn.huiounet.utils.qrcode.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/help")
public class HelpPayController {

    private static String fileUrl = "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/";

    @GetMapping("getQrCode")
    public String getQrCode(String text) throws Exception {


        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        if (text == null) {
            return "error:please enter text";
        }

        QRCodeUtil.encode(text, "/www/server/tomcat/apache-tomcat-8.5.51/webapps/imgs/0.png", fileUrl + format + ".jpg");
        return "https://xcx2.huiounet.cn/imgs/" + format + ".jpg";


    }
}

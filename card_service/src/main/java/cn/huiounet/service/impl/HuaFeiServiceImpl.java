package cn.huiounet.service.impl;

import cn.huiounet.pojo.huafei.HuaFei;
import cn.huiounet.service.HuaFeiService;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.md5.MD5Utils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yd
 * @version 1.0
 * @date 2020/1/5 22:57
 */
@Service
public class HuaFeiServiceImpl implements HuaFeiService {

    @Override
    public String JuHeHuaFei(String phoneNumber, String num) {
        HuaFei huaFei = new HuaFei();

        //电话号码 充值金额
        huaFei.setPhoneno(phoneNumber);
        huaFei.setCardnum(num);

        //秘钥
        huaFei.setKey("6804dadc2cf950de47c3ee0120d79cab");
        huaFei.setOpenid("JHcf23d254253185e88bb1e9751bdf81e6");

        //订单
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmSS");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        huaFei.setOrderid(retStrFormatNowDate);


        //签名校验值，md5(OpenID+key+phoneno+cardnum+orderid)，OpenID在个人中心查询
        String s = MD5Utils.stringToMD5("JHcf23d254253185e88bb1e9751bdf81e6" + "6804dadc2cf950de47c3ee0120d79cab"
                + phoneNumber + num + retStrFormatNowDate);
        huaFei.setSign(s);

        //服务器---聚合
        String s1 = HttpRequest.sendGet("http://op.juhe.cn/ofpay/mobile/onlineorder",
                "key=6804dadc2cf950de47c3ee0120d79cab&phoneno=" + phoneNumber + "&cardnum=" + num + "" +
                        "&orderid=" + retStrFormatNowDate + "&sign=" + s + "");

        return s1;
    }
}

package cn.huiounet.utils.juhe;

import cn.huiounet.utils.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JuHeCzUtils {

    @org.jetbrains.annotations.NotNull
    public static Boolean isPhoneCz(String phoneNum, String czNum){
        String key = "6804dadc2cf950de47c3ee0120d79cab";
        String s = HttpRequest.sendGet("http://op.juhe.cn/ofpay/mobile/telcheck", "cardnum=" + czNum + "&phoneno=" + phoneNum + "&key=" + key);

        JSONObject object = JSON.parseObject(s);

        String error_code = object.getString("error_code");

        if(error_code.equals("0")){
            return true;
        }else {
            return false;
        }
    }

    public static JSONObject phoneCzMoney(String phoneNum,String czNum){
        String key = "6804dadc2cf950de47c3ee0120d79cab";
        String s = HttpRequest.sendGet("http://op.juhe.cn/ofpay/mobile/telquery", "cardnum=" + czNum + "&phoneno=" + phoneNum + "&key=" + key);

        JSONObject object = JSON.parseObject(s);

        return object;
    }
}

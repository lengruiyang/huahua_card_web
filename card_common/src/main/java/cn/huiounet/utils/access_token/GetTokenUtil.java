package cn.huiounet.utils.access_token;

import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.wxPay.WeChatTool;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetTokenUtil {

    //请求地址
    public static final String wx_url_getToken="https://api.weixin.qq.com/cgi-bin/token";
    //https://api.weixin.qq.com/cgi-bin/token

    /**
     * 获得token的方法
     * @return
     */
    public static String getToken_wx(){
        String Requ_Of_Json = HttpRequest.sendGet(wx_url_getToken, "grant_type=client_credential&appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.app_key + "");
        //解析json数据包{"access_token":"ACCESS_TOKEN","expires_in":7200}

        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(Requ_Of_Json).getAsJsonObject();
        String token = jo.get("access_token").getAsString();


        return token;
    }


    /**
     * 获得微信公众号token的方法
     * @return
     */
    public static String getWxGZHToken_wx(){
        String Requ_Of_Json = HttpRequest.sendGet(wx_url_getToken, "grant_type=client_credential&appid=wx54817d38f066c5f5&secret=13735b7533d3e7a555ec38de19eba1a6");
        //解析json数据包{"access_token":"ACCESS_TOKEN","expires_in":7200}

        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(Requ_Of_Json).getAsJsonObject();
        System.out.println(jo);
        String token = jo.get("access_token").getAsString();


        return token;
    }

}

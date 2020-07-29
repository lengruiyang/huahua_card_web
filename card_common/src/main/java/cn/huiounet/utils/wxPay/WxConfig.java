package cn.huiounet.utils.wxPay;

import java.io.*;

public class WxConfig {

    private String wxpayid;
    private String key;
    private String appid;
    private String appkey;

    public WxConfig(String wxpayid, String key, String appid, String appkey) {
        this.wxpayid = wxpayid;
        this.key = key;
        this.appid = appid;
        this.appkey = appkey;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getWxpayid() {
        return wxpayid;
    }

    public void setWxpayid(String wxpayid) {
        this.wxpayid = wxpayid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public static WxConfig getConfig(){
        File file = new File("/www/config.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = null;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        try {
            while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = sb.toString();
        String[] split = str.split(";");
        String wxpayid = split[0];

        String key = split[1];

        String appid = split[2];
        String appkey = split[3];

        WxConfig wxConfig = new WxConfig(wxpayid,key,appid,appkey);

        return wxConfig;
    }
}

package cn.huiounet.utils.wxUtils;



import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WxUpload  {

    private static Logger logger = Logger.getLogger(WxUpload.class);

    /**
     * 上传其它类型永久素材
     *
     * @param accessToken
     *            接口访问凭证
     * @param type
     *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file)
     * @param mediaFileUrl
     *            媒体文件的url 上传的媒体文件限制 图片（image）:1MB，支持JPG格式
     *            语音（voice）：2MB，播放长度不超过60s，支持AMR格式 视频（video）：10MB，支持MP4格式
     *            普通文件（file）：10MB
     */
    public static String uploadForeverMedia(String accessToken, String type,
                                            String mediaFileUrl) {
        String ForeverMediaId = "";
        // 拼装请求地址
        String uploadMediaUrl = "https://qyapi.weixin.qq.com/cgi-bin/material/add_material?type=TYPE&access_token=ACCESS_TOKEN";
        uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken)
                .replace("TYPE", type);

        // 定义数据分隔符
        String boundary = "------------7da2e536604c8";
        System.out.println(uploadMediaUrl);
        try {

            URL uploadUrl = new URL(uploadMediaUrl);
            HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
                    .openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod("POST");
            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            // 获取媒体文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream = uploadConn.getOutputStream();

            URL mediaUrl = new URL(mediaFileUrl);
            HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
                    .openConnection();
            meidaConn.setDoOutput(true);
            meidaConn.setRequestMethod("GET");

            // 从请求头中获取内容类型
            String contentType = meidaConn.getHeaderField("Content-Type");
            //获取文件的原始名称
            String originalFilename = contentType;//timg (1).jpg
            //获取最后一个.的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String suffix = originalFilename.substring(lastIndexOf);
            // 请求体开始
            outputStream.write(("--" + boundary + "\r\n").getBytes());
            outputStream
                    .write(String
                            .format(
                                    "Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",
                                    suffix).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n",
                    contentType).getBytes());

            // 获取媒体文件的输入流（读取文件）
            BufferedInputStream bis = new BufferedInputStream(meidaConn
                    .getInputStream());
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                // 将媒体文件写到输出流（往微信服务器写数据）
                outputStream.write(buf, 0, size);
            }
            // 请求体结束
            outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
            outputStream.close();
            bis.close();
            meidaConn.disconnect();

            // 获取媒体文件上传的输入流（从微信服务器读数据）
            InputStream inputStream = uploadConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            uploadConn.disconnect();
            // 使用JSON-lib解析返回结果
            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
            // 测试打印结果
            logger.info("打印测试结果" + jsonObject);
            if (jsonObject != null) {
                if (jsonObject.getInt("errcode") == 0) {
                    ForeverMediaId = jsonObject.getString("media_id");
                }
            }
            // type等于 缩略图（thumb） 时的返回结果和其它类型不一样
        } catch (Exception e) {
            String error = String.format("上传媒体文件失败：%s", e);
            logger.error(error);
        }
        return ForeverMediaId;
    }
}

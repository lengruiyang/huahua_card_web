package cn.huiounet.service.impl;

import cn.huiounet.pojo.live.CreateLiveGoodsPojo;
import cn.huiounet.pojo.live.CreateLivePojo;
import cn.huiounet.pojo.live.GetLiveLogsPojo;
import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.liveGoods.AddLiveGoods;
import cn.huiounet.service.LiveService;
import cn.huiounet.utils.http.HttpRequest;
import cn.huiounet.utils.redis.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LiveServiceImpl implements LiveService {
    public static final String createLiveUrl = "https://api.weixin.qq.com/wxaapi/broadcast/room/create?access_token=";
    public static final String getLiveUrl = "https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=";
    public static final String getVideoLogs = "https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=";
    public static final String addGoodsToVideo = "https://api.weixin.qq.com/wxaapi/broadcast/room/addgoods?access_token=";
    public static final String addGoods = "https://api.weixin.qq.com/wxaapi/broadcast/goods/add?access_token=";
//    String token = RedisUtil.redisGetString("token");

    String token = "40_I6aNeOreFCLZA1bsI2y1iQuRxtf2YWXcq9gcmS3K9wtVg609FKFXdX_MGRmP0bdLJzFzfwb01BZzQw-iV7Fgsy0ohmSRO_D7V0FYRgn1Jj0UaO9wWJHqzrVfwIQxFUFqHs-8h3DukqIDq3hEFPOaAGAUED";
    /**
     * 创建直播间
     * @param createLivePojo
     * @return
     */
    @Override
    public String createLive(CreateLivePojo createLivePojo) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(createLivePojo);
        String stuString = JSONObject.toJSONString(jsonObject);
        String createMess = HttpRequest.sendPost(createLiveUrl + token, stuString);
        return createMess;
    }

    /**
     * 获取直播间
     * @param liveSys
     * @return
     */
    @Override
    public String getLiveRooms(LiveSys liveSys) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(liveSys);
        String liveJson = JSONObject.toJSONString(jsonObject);
        String liveRooms = HttpRequest.sendPost(getLiveUrl + token, liveJson);
        return liveRooms;
    }

    /**
     * 获取回放
     * @param getLiveLogsPojo
     * @return
     */
    @Override
    public String getLiveLogs(GetLiveLogsPojo getLiveLogsPojo) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(getLiveLogsPojo);
        String liveJson = JSONObject.toJSONString(jsonObject);
        String videoLogs = HttpRequest.sendPost(getVideoLogs + token, liveJson);
        return videoLogs;
    }

    /**
     * 给直播间增加商品
     * @param createLiveGoodsPojo
     * @return
     */
    @Override
    public String addGoodsToLive(CreateLiveGoodsPojo createLiveGoodsPojo) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(createLiveGoodsPojo);
        String liveJson = JSONObject.toJSONString(jsonObject);
        String addResult = HttpRequest.sendPost(addGoodsToVideo + token, liveJson);
        return addResult;
    }

    /**
     * 添加商品到商品库
     * @param addLiveGoods
     * @return
     */
    @Override
    public String addGoods(AddLiveGoods addLiveGoods) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(addLiveGoods);
        String liveJson = JSONObject.toJSONString(jsonObject);
        String addResult = HttpRequest.sendPost(addGoods + token, liveJson);
        return addResult;
    }
}

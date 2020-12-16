package cn.huiounet.service;

import cn.huiounet.pojo.live.CreateLiveGoodsPojo;
import cn.huiounet.pojo.live.CreateLivePojo;
import cn.huiounet.pojo.live.GetLiveLogsPojo;
import cn.huiounet.pojo.live.LiveSys;
import cn.huiounet.pojo.liveGoods.AddLiveGoods;

public interface LiveService {

    /**
     * 创建直播间
     * @param createLivePojo
     * @return
     */
    String createLive(CreateLivePojo createLivePojo);

    /**
     * 获取直播间
     * @param liveSys
     * @return
     */
    String getLiveRooms(LiveSys liveSys);

    /**
     * 获取直播回放
     * @param getLiveLogsPojo
     * @return
     */
    String getLiveLogs(GetLiveLogsPojo getLiveLogsPojo);

    /**
     * 给直播间添加商品
     * @param createLiveGoodsPojo
     * @return
     */
    String addGoodsToLive(CreateLiveGoodsPojo createLiveGoodsPojo);

    /**
     * 新增商品
     * @param addLiveGoods
     * @return
     */
    String addGoods(AddLiveGoods addLiveGoods);
}

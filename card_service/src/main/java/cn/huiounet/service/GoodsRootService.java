package cn.huiounet.service;

import cn.huiounet.pojo.root.GoodsRoot;

import java.util.List;

public interface GoodsRootService  {

    void save(GoodsRoot goodsRoot);

    List<GoodsRoot> findByUserId(String user_id,int start,int size);
}

package cn.huiounet.service;

import cn.huiounet.pojo.pingjia.PingJiaSys;

import java.util.List;

public interface PingJiaSysService {

    List<PingJiaSys> findByUser_id(String user_id,int start,int size);

    List<PingJiaSys> findByOrderNum(String order_num);

    void savePingJia(PingJiaSys pingJiaSys);

    void deleteById(String id);

    int findByNum(String star,String goods_id);

    List<PingJiaSys> GoodsPj(String goods_id,int start,int size);
    List<PingJiaSys> PoorPj(String goods_id,int start,int size);
    List<PingJiaSys> MPj(String goods_id,int start,int size);

    List<PingJiaSys> findByImg(String goods_id,int start,int size);

    List<PingJiaSys> findByUserGoodsId( String goods_id,String user_id);


    List<PingJiaSys> findByGoodsId(String goods_id,int start,int size);
}

package cn.huiounet.dao;

import cn.huiounet.pojo.diandan.OrderDianDanQrCode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderDianDanQrCodeMapper extends Mapper<OrderDianDanQrCode> {


    List<OrderDianDanQrCode> findByShopId(String shop_id);

}

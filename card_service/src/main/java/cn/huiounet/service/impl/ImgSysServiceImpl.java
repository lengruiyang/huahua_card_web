package cn.huiounet.service.impl;

import cn.huiounet.dao.ImgSysMapper;
import cn.huiounet.pojo.img.ImgSys;
import cn.huiounet.service.ImgSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgSysServiceImpl implements ImgSysService {
    @Autowired
    private ImgSysMapper imgSysMapper;

    @Override
    public List<ImgSys> findByGoodsId(String goods_id) {
        return imgSysMapper.findByGoodsId(goods_id);
    }

    @Override
    public List<ImgSys> findByLunBo() {
        return imgSysMapper.findByLunBo();
    }
}

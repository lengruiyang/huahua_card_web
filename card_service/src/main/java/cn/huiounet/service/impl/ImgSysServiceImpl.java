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

    @Override
    public List<ImgSys> findByFenLeiLunBo() {
        return imgSysMapper.findByFenLeiLunBo();
    }

    @Override
    public void saveImg(ImgSys imgSys) {
        imgSysMapper.insert(imgSys);
    }

    @Override
    public List<ImgSys> findByFenLeiLunBoShouYe() {
        return imgSysMapper.findByFenLeiLunBoShouYe();
    }

    @Override
    public List<ImgSys> findByFenLeiLunBoBystatus(String status) {
        return imgSysMapper.findByFenLeiLunBoBystatus(status);
    }

    @Override
    public void deleteById(int id) {
        imgSysMapper.deleteById(id);
    }

    @Override
    public void updateById(String url, String to_url, String goods_id, int id) {
        imgSysMapper.updateById(url, to_url, goods_id, id);
    }
}

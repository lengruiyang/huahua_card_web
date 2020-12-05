package cn.huiounet.pojo.img;

import java.io.Serializable;

public class ImgSysAdd implements Serializable {
    private ImgSys imgSys;
    private String name;

    public ImgSysAdd(ImgSys imgSys, String name) {
        this.imgSys = imgSys;
        this.name = name;
    }

    public ImgSys getImgSys() {
        return imgSys;
    }

    public void setImgSys(ImgSys imgSys) {
        this.imgSys = imgSys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

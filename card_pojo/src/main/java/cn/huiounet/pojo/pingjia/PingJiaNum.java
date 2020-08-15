package cn.huiounet.pojo.pingjia;

import java.io.Serializable;

public class PingJiaNum implements Serializable {
    private int goodPj;
    private int poorPj;
    private int mPj;
    private int imgPj;
    private int allPj;

    public int getGoodPj() {
        return goodPj;
    }

    public int getAllPj() {
        return allPj;
    }

    public void setAllPj(int allPj) {
        this.allPj = allPj;
    }

    public void setGoodPj(int goodPj) {
        this.goodPj = goodPj;
    }

    public int getPoorPj() {
        return poorPj;
    }

    public void setPoorPj(int poorPj) {
        this.poorPj = poorPj;
    }

    public int getmPj() {
        return mPj;
    }

    public void setmPj(int mPj) {
        this.mPj = mPj;
    }

    public int getImgPj() {
        return imgPj;
    }

    public void setImgPj(int imgPj) {
        this.imgPj = imgPj;
    }

    public PingJiaNum(int goodPj, int poorPj, int mPj, int imgPj,int allPj) {
        this.goodPj = goodPj;
        this.poorPj = poorPj;
        this.mPj = mPj;
        this.imgPj = imgPj;
        this.allPj = allPj;
    }
}

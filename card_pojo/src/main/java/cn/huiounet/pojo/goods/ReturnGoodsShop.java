package cn.huiounet.pojo.goods;

import java.io.Serializable;
import java.util.List;

public class ReturnGoodsShop implements Serializable{
    private GoodsSys goodsSys;
    private List<GoodsColor> goodsColors;

    public ReturnGoodsShop(GoodsSys goodsSys, List<GoodsColor> goodsColors) {
        this.goodsSys = goodsSys;
        this.goodsColors = goodsColors;
    }

    public GoodsSys getGoodsSys() {
        return goodsSys;
    }

    public void setGoodsSys(GoodsSys goodsSys) {
        this.goodsSys = goodsSys;
    }

    public List<GoodsColor> getGoodsColors() {
        return goodsColors;
    }

    public void setGoodsColors(List<GoodsColor> goodsColors) {
        this.goodsColors = goodsColors;
    }
}

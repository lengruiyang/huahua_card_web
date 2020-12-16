package cn.huiounet.pojo.liveGoods;

public class GoodsInfo {
    private String coverImgUrl;
    private String name;
    private int priceType; //价格类型
    private int price;
    private int price2;
    private String url;
    private String thirdPartyAppid;

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThirdPartyAppid() {
        return thirdPartyAppid;
    }

    public void setThirdPartyAppid(String thirdPartyAppid) {
        this.thirdPartyAppid = thirdPartyAppid;
    }

    public GoodsInfo() {
    }
}

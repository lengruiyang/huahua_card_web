package cn.huiounet.pojo.live;

/**
 *
 name: "测试直播房间1",  // 房间名字
 coverImg: "",   // 通过 uploadfile 上传，填写 mediaID
 startTime: 1588237130,   // 开始时间
 endTime: 1588237130 , // 结束时间
 anchorName: "zefzhang1",  // 主播昵称
 anchorWechat: "WxgQiao_04",  // 主播微信号
 subAnchorWechat: "WxgQiao_03",  // 主播副号微信号
 createrWechat: 'test_creater', // 创建者微信号
 shareImg: "" ,  //通过 uploadfile 上传，填写 mediaID
 feedsImg: "",   //通过 uploadfile 上传，填写 mediaID
 isFeedsPublic: 1, // 是否开启官方收录，1 开启，0 关闭
 type: 1 , // 直播类型，1 推流 0 手机直播
 closeLike: 0 , // 是否关闭点赞 1：关闭
 closeGoods: 0, // 是否关闭商品货架，1：关闭
 closeComment: 0 // 是否开启评论，1：关闭
 closeReplay: 1 , // 是否关闭回放 1 关闭
 closeShare: 0,   //  是否关闭分享 1 关闭
 closeKf: 0, // 是否关闭客服，1 关闭
 */
public class CreateLivePojo {
    private String name;
    private String coverImg;
    private String startTime;
    private String endTime;
    private String anchorName;
    private String anchorWechat;
    private String subAnchorWechat;
    private String createrWechat;
    private String shareImg;
    private String feedsImg;
    private String isFeedsPublic;
    private String type;
    private String closeLike;
    private String closeGoods;
    private String closeComment;
    private String closeReplay;
    private String closeShare;
    private String closeKf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getAnchorWechat() {
        return anchorWechat;
    }

    public void setAnchorWechat(String anchorWechat) {
        this.anchorWechat = anchorWechat;
    }

    public String getSubAnchorWechat() {
        return subAnchorWechat;
    }

    public void setSubAnchorWechat(String subAnchorWechat) {
        this.subAnchorWechat = subAnchorWechat;
    }

    public String getCreaterWechat() {
        return createrWechat;
    }

    public void setCreaterWechat(String createrWechat) {
        this.createrWechat = createrWechat;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getFeedsImg() {
        return feedsImg;
    }

    public void setFeedsImg(String feedsImg) {
        this.feedsImg = feedsImg;
    }

    public String getIsFeedsPublic() {
        return isFeedsPublic;
    }

    public void setIsFeedsPublic(String isFeedsPublic) {
        this.isFeedsPublic = isFeedsPublic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCloseLike() {
        return closeLike;
    }

    public void setCloseLike(String closeLike) {
        this.closeLike = closeLike;
    }

    public String getCloseGoods() {
        return closeGoods;
    }

    public void setCloseGoods(String closeGoods) {
        this.closeGoods = closeGoods;
    }

    public String getCloseComment() {
        return closeComment;
    }

    public void setCloseComment(String closeComment) {
        this.closeComment = closeComment;
    }

    public String getCloseReplay() {
        return closeReplay;
    }

    public void setCloseReplay(String closeReplay) {
        this.closeReplay = closeReplay;
    }

    public String getCloseShare() {
        return closeShare;
    }

    public void setCloseShare(String closeShare) {
        this.closeShare = closeShare;
    }

    public String getCloseKf() {
        return closeKf;
    }

    public void setCloseKf(String closeKf) {
        this.closeKf = closeKf;
    }

    public CreateLivePojo(String name, String coverImg, String startTime, String endTime, String anchorName, String anchorWechat, String subAnchorWechat, String createrWechat, String shareImg, String feedsImg, String isFeedsPublic, String type, String closeLike, String closeGoods, String closeComment, String closeReplay, String closeShare, String closeKf) {
        this.name = name;
        this.coverImg = coverImg;
        this.startTime = startTime;
        this.endTime = endTime;
        this.anchorName = anchorName;
        this.anchorWechat = anchorWechat;
        this.subAnchorWechat = subAnchorWechat;
        this.createrWechat = createrWechat;
        this.shareImg = shareImg;
        this.feedsImg = feedsImg;
        this.isFeedsPublic = isFeedsPublic;
        this.type = type;
        this.closeLike = closeLike;
        this.closeGoods = closeGoods;
        this.closeComment = closeComment;
        this.closeReplay = closeReplay;
        this.closeShare = closeShare;
        this.closeKf = closeKf;
    }

    public CreateLivePojo() {
    }
}

package cn.huiounet.pojo.img;

/**
 * @author yd
 * @version 1.0
 * @date 2020/4/10 23:53
 */
public class Image_sys {
    private String url;
    private String name;

    public Image_sys() {
    }

    public String getUrl() {
        return url;
    }

    public Image_sys(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

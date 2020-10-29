package cn.huiounet.pojo.root;

import java.util.List;

public class ReturnGoodsRoot {
    private GoodsRoot roots;
    private String time;

    public GoodsRoot getRoots() {
        return roots;
    }

    public void setRoots(GoodsRoot roots) {
        this.roots = roots;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ReturnGoodsRoot(GoodsRoot roots, String time) {
        this.roots = roots;
        this.time = time;
    }
}

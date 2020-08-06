package cn.huiounet.pojo.live;

import java.io.Serializable;

public class LiveSys implements Serializable {
    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LiveSys(int start, int limit) {
        this.start = start;
        this.limit = limit;
    }
}

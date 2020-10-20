package cn.huiounet.pojo.coupon;

import java.io.Serializable;

public class ReturnCoupon implements Serializable {
    private CouPon couPon;
    private String is_get;

    public CouPon getCouPon() {
        return couPon;
    }

    @Override
    public String toString() {
        return "ReturnCoupon{" +
                "couPon=" + couPon +
                ", is_get='" + is_get + '\'' +
                '}';
    }

    public void setCouPon(CouPon couPon) {
        this.couPon = couPon;
    }

    public String getIs_get() {
        return is_get;
    }

    public void setIs_get(String is_get) {
        this.is_get = is_get;
    }

    public ReturnCoupon(CouPon couPon, String is_get) {
        this.couPon = couPon;
        this.is_get = is_get;
    }

    public ReturnCoupon() {
    }
}

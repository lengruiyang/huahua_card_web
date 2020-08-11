package cn.huiounet.pojo.order;

import java.io.Serializable;

public class ReturnOrderNum implements Serializable {
    private int not_pay;
    private int is_payed;
    private int is_cancel;
    private int is_fa_huo;

    public ReturnOrderNum(int not_pay, int is_payed, int is_cancel, int is_fa_huo) {
        this.not_pay = not_pay;
        this.is_payed = is_payed;
        this.is_cancel = is_cancel;
        this.is_fa_huo = is_fa_huo;
    }

    public int getNot_pay() {
        return not_pay;
    }

    public void setNot_pay(int not_pay) {
        this.not_pay = not_pay;
    }

    public int getIs_payed() {
        return is_payed;
    }

    public void setIs_payed(int is_payed) {
        this.is_payed = is_payed;
    }

    public int getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(int is_cancel) {
        this.is_cancel = is_cancel;
    }

    public int getIs_fa_huo() {
        return is_fa_huo;
    }

    public void setIs_fa_huo(int is_fa_huo) {
        this.is_fa_huo = is_fa_huo;
    }
}

package cn.huiounet.pojo.diandan;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_order_diandan_qrcode")
public class OrderDianDanQrCode implements Serializable {
    private int id;
    private String shop_id;
    private String qr_code_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getQr_code_url() {
        return qr_code_url;
    }

    public void setQr_code_url(String qr_code_url) {
        this.qr_code_url = qr_code_url;
    }

    public OrderDianDanQrCode() {
    }
}

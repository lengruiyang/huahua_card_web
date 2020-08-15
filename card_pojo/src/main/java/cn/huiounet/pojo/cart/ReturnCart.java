package cn.huiounet.pojo.cart;

import java.io.Serializable;
import java.util.List;

public class ReturnCart implements Serializable {
    private String shop_id;
    private String shop_name;
    private String shop_img;
    private List<CartSys> cartSys;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public List<CartSys> getCartSys() {
        return cartSys;
    }

    public void setCartSys(List<CartSys> cartSys) {
        this.cartSys = cartSys;
    }

    public ReturnCart(String shop_id, String shop_name, String shop_img, List<CartSys> cartSys) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_img = shop_img;
        this.cartSys = cartSys;
    }
}

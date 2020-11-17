package cn.huiounet.pojo.order;

import cn.huiounet.pojo.UserInfoSystem;
import cn.huiounet.pojo.address.AddressSys;
import cn.huiounet.pojo.pingjia.PingJiaSys;
import cn.huiounet.pojo.shop.ShopSys;

import java.io.Serializable;
import java.util.List;

public class OrderReturnSYs implements Serializable {
    private OrderSys orderSys;
    private List<ReturnGoods> returnGoods;
    private UserInfoSystem userInfoSystem;
    private OrderAddress addressSys;
    private ShopSys shopSys;
    private PingJiaSys pingJiaSys;

    public PingJiaSys getPingJiaSys() {
        return pingJiaSys;
    }

    public void setPingJiaSys(PingJiaSys pingJiaSys) {
        this.pingJiaSys = pingJiaSys;
    }

    public OrderReturnSYs() {
    }

    public OrderSys getOrderSys() {
        return orderSys;
    }

    public void setOrderSys(OrderSys orderSys) {
        this.orderSys = orderSys;
    }

    public List<ReturnGoods> getReturnGoods() {
        return returnGoods;
    }

    public void setReturnGoods(List<ReturnGoods> returnGoods) {
        this.returnGoods = returnGoods;
    }

    public UserInfoSystem getUserInfoSystem() {
        return userInfoSystem;
    }

    public void setUserInfoSystem(UserInfoSystem userInfoSystem) {
        this.userInfoSystem = userInfoSystem;
    }

    public OrderAddress getAddressSys() {
        return addressSys;
    }

    public void setAddressSys(OrderAddress addressSys) {
        this.addressSys = addressSys;
    }

    public ShopSys getShopSys() {
        return shopSys;
    }

    public void setShopSys(ShopSys shopSys) {
        this.shopSys = shopSys;
    }

    public OrderReturnSYs(OrderSys orderSys, List<ReturnGoods> returnGoods, UserInfoSystem userInfoSystem, OrderAddress addressSys, ShopSys shopSys) {
        this.orderSys = orderSys;
        this.returnGoods = returnGoods;
        this.userInfoSystem = userInfoSystem;
        this.addressSys = addressSys;
        this.shopSys = shopSys;
    }
}

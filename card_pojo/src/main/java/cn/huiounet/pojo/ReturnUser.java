package cn.huiounet.pojo;

public class ReturnUser {
    private UserInfoSystem userInfoSystem;
    private String acc_token;

    public ReturnUser(UserInfoSystem userInfoSystem, String acc_token) {
        this.userInfoSystem = userInfoSystem;
        this.acc_token = acc_token;
    }

    @Override
    public String toString() {
        return "ReturnUser{" +
                "userInfoSystem=" + userInfoSystem +
                ", acc_token='" + acc_token + '\'' +
                '}';
    }

    public UserInfoSystem getUserInfoSystem() {
        return userInfoSystem;
    }

    public void setUserInfoSystem(UserInfoSystem userInfoSystem) {
        this.userInfoSystem = userInfoSystem;
    }

    public String getAcc_token() {
        return acc_token;
    }

    public void setAcc_token(String acc_token) {
        this.acc_token = acc_token;
    }
}

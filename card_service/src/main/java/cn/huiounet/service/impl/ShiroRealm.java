package cn.huiounet.service.impl;

import cn.huiounet.pojo.UserSys;
import cn.huiounet.service.UserSysService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthenticatingRealm {

    @Autowired
    private UserSysService userMapper;
    /**
     *  登录的验证实现方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token2 = (UsernamePasswordToken) token;
        String username = token2.getUsername();
        UserSys byUserName = userMapper.findByUserName(username);
        if(byUserName == null) {
            throw new UnknownAccountException("noUser");
        }
//        if(byUserName.getStatus() == 0) {
//            throw new UnknownAccountException("用户名已被禁用，请联系系统管理员！");
//        }

        /**
         * principals: 可以使用户名，或d登录用户的对象
         * hashedCredentials: 从数据库中获取的密码
         * credentialsSalt：密码加密的盐值
         * RealmName:  类名（ShiroRealm）
         */
        AuthenticationInfo info = new SimpleAuthenticationInfo(byUserName, byUserName.getPassword(), null, getName());
        return info; //框架完成验证
    }

}
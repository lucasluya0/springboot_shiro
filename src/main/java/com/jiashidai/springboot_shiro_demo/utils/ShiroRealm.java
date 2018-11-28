package com.jiashidai.springboot_shiro_demo.utils;

import com.jiashidai.springboot_shiro_demo.entity.Role;
import com.jiashidai.springboot_shiro_demo.entity.User;
import com.jiashidai.springboot_shiro_demo.services.UserRoleService;
import com.jiashidai.springboot_shiro_demo.services.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;
    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = userService.getByUserName((String) principalCollection.getPrimaryPrincipal());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //赋予角色
        for(Role role:userRoleService.getRoles(user.getUserId())){
            info.addRole(role.getRoleKey());
        }
//        //赋予权限
//        for(Permission permission:permissionService.getByUserId(user.getId())){
//            if(StringUtils.isNotBlank(permission.getPermCode()))
//            info.addStringPermission(permission.getName());
//        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("jinru");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();
        User user = userService.getByUserName(userName);
        if (user != null) {
            return new SimpleAuthenticationInfo(userName,user.getLoginPass(),ByteSource.Util.bytes(user.getSalt()),getName());
        } else {
            return null;
        }

    }
}

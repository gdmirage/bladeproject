package com.blade.manager.system.modules.security.model;

import java.io.Serializable;

/**
 * 认证信息
 *
 * @author Zheng Jie
 * @date 2018-11-23
 * 返回token
 */
public class AuthenticationInfo implements Serializable {

    private String token;

    private LoginUser loginUser;

    public AuthenticationInfo(String token, LoginUser loginUser) {
        this.token = token;
        this.loginUser = loginUser;
    }

    public String getToken() {
        return token;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }
}

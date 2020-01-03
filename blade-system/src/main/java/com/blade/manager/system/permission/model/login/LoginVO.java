package com.blade.manager.system.permission.model.login;

import com.blade.core.model.base.JsonAble;

/**
 * 登陆成功后的对象信息
 *
 * @author blade
 * 2019/12/20 18:04
 */
public class LoginVO extends JsonAble {

    private static final long serialVersionUID = -4924414989596568306L;

    /**
     * token信息
     */
    private String token;

    /**
     * 登陆用户
     */
    private LoginUser loginUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

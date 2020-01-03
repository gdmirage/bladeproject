package com.blade.manager.system.permission.model.login;

import com.blade.core.model.base.JsonAble;

/**
 * 登陆查询的信息
 *
 * @author blade
 * 2019/12/20 18:05
 */
public class LoginDTO extends JsonAble{

    private static final long serialVersionUID = -8351130694224236108L;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 对应验证码的UUID
     */
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

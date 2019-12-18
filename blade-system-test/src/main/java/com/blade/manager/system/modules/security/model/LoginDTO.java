package com.blade.manager.system.modules.security.model;

import javax.validation.constraints.NotNull;

/**
 * 登陆
 *
 * @author blade
 * 2019/9/19 15:58
 */
public class LoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String uuid;

    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}

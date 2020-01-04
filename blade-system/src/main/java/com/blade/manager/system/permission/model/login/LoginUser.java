package com.blade.manager.system.permission.model.login;

import com.blade.core.model.base.JsonAble;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 * 登陆用户
 *
 * @author Blade
 * @date 2020/1/3 15:05
 */
public class LoginUser extends JsonAble {

    private static final long serialVersionUID = 8850080290574496965L;
    private Long userId;

    private String loginName;

    private String username;

    private String avatar;

    private String email;

    private String mobilePhone;

    private String deptName;

    private String jobName;

    private List<String> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

package com.blade.manager.system.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    private Integer jobId;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 移动号码
     */
    private String mobilePhone;

    /**
     * 固话
     */
    private String telPhone;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态(1、enabled 2、disabled
     */
    private String status;

    /**
     * 最后修改密码日期
     */
    private LocalDateTime lastPasswordResetTime;

    /**
     * 上一次登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 上一次退出时间
     */
    private LocalDateTime lastLogoutTime;

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobId() {
        return this.jobId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getDeptId() {
        return this.deptId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getTelPhone() {
        return this.telPhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setLastPasswordResetTime(LocalDateTime lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    public LocalDateTime getLastPasswordResetTime() {
        return this.lastPasswordResetTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLogoutTime(LocalDateTime lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public LocalDateTime getLastLogoutTime() {
        return this.lastLogoutTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

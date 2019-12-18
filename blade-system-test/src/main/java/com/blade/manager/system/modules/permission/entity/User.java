package com.blade.manager.system.modules.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 最后修改密码的日期
     */
    private LocalDateTime lastPasswordResetTime;

    private Long deptId;

    private String phone;

    private Long jobId;

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

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLastPasswordResetTime() {
        return lastPasswordResetTime;
    }

    public void setLastPasswordResetTime(LocalDateTime lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}

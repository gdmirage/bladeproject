package com.blade.manager.system.modules.permission.model.user;

import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.model.dept.DeptVO;
import com.blade.manager.system.modules.permission.model.job.JobVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author blade
 * 2019/11/15 10:33
 */
public class UserListVO implements Serializable {
    private static final long serialVersionUID = -778839750534520429L;

    private Long id;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态：1启用、0禁用
     */
    private Boolean enabled;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastPasswordResetTime;

    private DeptVO dept;

    private String phone;

    private JobVO job;

    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
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

    public DeptVO getDept() {
        return dept;
    }

    public void setDept(DeptVO dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public JobVO getJob() {
        return job;
    }

    public void setJob(JobVO job) {
        this.job = job;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

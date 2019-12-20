package com.blade.manager.system.permission.model;

import java.io.Serializable;
import com.blade.core.model.request.PageSearchDTO;
import java.time.LocalDateTime;

/**
 * <p>
 *  分页查询条件
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class UserPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long id;


    /**
     * 创建人
     */
    private String creator;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 修改人
     */
    private String modifier;


    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    /**
     * 是否删除。0-未删除。1-已删除
     */
    private Integer isDelete;


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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public LocalDateTime getModifyTime() {
        return this.modifyTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() {
        return this.isDelete;
    }

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

}

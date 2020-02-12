package com.blade.manager.system.permission.model.user;

import com.blade.core.model.base.JsonAble;

import java.util.List;

/**
 * TODO:
 * 用户新增或编辑的实体
 *
 * @author blade
 * 2020/2/10 15:14
 */
public class UserInsertOrUpdateDTO extends JsonAble {
    private static final long serialVersionUID = 2062047006425183392L;

    /**
     * 用户ID
     */
    private Long id;

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
     * 状态(1、enabled 2、disabled)
     */
    private String status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

package com.blade.manager.system.permission.model.user;

import com.blade.core.model.request.PageSearchDTO;

/**
 * <p>
 * 分页查询条件
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class UserPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间-开始
     */
    private String startTime;

    /**
     * 创建时间-结束
     */
    private String endTime;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 移动号码
     */
    private String mobilePhone;

    /**
     * 状态(1、enabled 2、disabled)
     */
    private String status;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 查询字段
     */
    private String queryValue;

    public String getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

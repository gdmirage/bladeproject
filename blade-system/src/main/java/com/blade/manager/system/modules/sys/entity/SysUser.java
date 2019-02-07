package com.blade.manager.system.modules.sys.entity;

import java.util.Date;

public class SysUser {
    private Long id;

    private String createMan;

    private Date createDate;

    private String updateMan;

    private Date updateDate;

    private Byte isDelete;

    private String loginName;

    private String password;

    private String userName;

    private Byte sex;

    private String email;

    private String phone;

    private String mobile;

    private String loginIp;

    private Date loginDate;

    public SysUser(Long id, String createMan, Date createDate, String updateMan, Date updateDate, Byte isDelete, String loginName, String password, String userName, Byte sex, String email, String phone, String mobile, String loginIp, Date loginDate) {
        this.id = id;
        this.createMan = createMan;
        this.createDate = createDate;
        this.updateMan = updateMan;
        this.updateDate = updateDate;
        this.isDelete = isDelete;
        this.loginName = loginName;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.loginIp = loginIp;
        this.loginDate = loginDate;
    }

    public SysUser() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateMan() {
        return updateMan;
    }

    public void setUpdateMan(String updateMan) {
        this.updateMan = updateMan == null ? null : updateMan.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
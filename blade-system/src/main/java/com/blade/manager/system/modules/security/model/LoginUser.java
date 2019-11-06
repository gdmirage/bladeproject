package com.blade.manager.system.modules.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blade
 * 2019/9/20 10:32
 */
public class LoginUser implements Serializable{
    private static final long serialVersionUID = -2832829606837390915L;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private String dept;

    private String job;

    private List<String> roles = new ArrayList<>();

    public LoginUser(String username, String avatar, String email, String phone, String dept, String job) {
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.job = job;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

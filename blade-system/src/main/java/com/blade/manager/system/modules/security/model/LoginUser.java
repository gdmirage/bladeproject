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

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDept() {
        return dept;
    }

    public String getJob() {
        return job;
    }

    public List<String> getRoles() {
        return roles;
    }
}

package com.blade.practice.generator;

import java.sql.Connection;

/**
 * @author blade
 * 2019/10/28 10:22
 */
public abstract class AbstractDbConfig {

    protected String dbUrl;
    protected String user;
    protected String password;

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 Connection 信息
     * @return {@link Connection}
     */
    abstract Connection getConnection();
}

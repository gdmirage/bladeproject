package com.blade.practice.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author blade
 * 2019/10/28 10:24
 */
public class MysqlDbConfig extends AbstractDbConfig {

    private String driver = "com.mysql.jdbc.Driver";

    private Connection connection = null;

    public String getDriver() {
        return driver;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(this.driver);
            connection = DriverManager.getConnection(super.dbUrl, super.user, super.password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

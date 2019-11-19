package com.blade.generator.core.config;

import com.blade.generator.core.config.AbstractDbConfig;

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

package com.blade.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO:
 * 主要是用来创建数据库连接
 * 这个是mysql的数据库连接工具类，后续看看是否可以扩展到别的数据库
 *
 * @author chenjiangxin
 * @date 2018/12/14 9:29
 */
public class MysqlConnectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlConnectionUtil.class);

    private static Connection CONNECTION;
    private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";

    private MysqlConnectionUtil(){

    }

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            if (null == CONNECTION) {
                synchronized (MysqlConnectionUtil.class) {
                    if (null == CONNECTION) {
                        CONNECTION = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    }
                }
            }
            return CONNECTION;
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("create mysql connection exception ", e);
            return null;
        }
    }

    private static Statement getStatement() {
        if (null == CONNECTION) {
            getConnection();
        }

        try {
            return CONNECTION.createStatement();
        } catch (SQLException e) {
            LOGGER.error("create mysql statement exception ", e);
            return null;
        }
    }

    private static void close(Connection connection, Statement statement) {
        closeStatement(statement);
        closeConnection(connection);
    }

    private static void closeStatement(Statement statement) {
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("close statement exception ", e);
            }
        }
    }

    private static void closeConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection exception ", e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = MysqlConnectionUtil.getConnection();
        Statement statement = MysqlConnectionUtil.getStatement();
        String sql = "select * from score";
        if (null == statement) {
            LOGGER.warn("statement is null ");
            return;
        }
        statement.execute(sql);
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            System.out.println(rs.getString("s_id"));
        }
        close(connection, statement);
    }
}

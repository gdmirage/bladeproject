package com.blade.practice.generator;

import com.alibaba.fastjson.JSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blade
 * 2019/10/23 16:51
 */
public class Generator {

    public static void main(String[] args) {
        Connection connection = getConnection();
        try {

            ResultSet tableNames = connection.getMetaData().getTables(connection.getCatalog(), "%", "user", null);

            Table table = new Table();

            while (tableNames.next()) {

                String tableName = tableNames.getString("TABLE_NAME");
                String remarks = tableNames.getString("REMARKS");
                table.setTableName(tableName);
                table.setRemark(remarks);
//                System.out.println(tableName + " -- " + remarks);

                List<Column> columns = new ArrayList<>();
                ResultSet columnRs = connection.getMetaData().getColumns(null, null, tableName, null);
                while (columnRs.next()) {
                    String columnName = columnRs.getString("COLUMN_NAME");
                    String dataType = columnRs.getString("TYPE_NAME");
                    Column column = new Column();
                    column.setColumnName(columnName);
                    column.setColumnJdbcType(dataType);
                    columns.add(column);
                    table.setColumns(columns);
                }
                System.out.println(JSON.toJSONString(table));
                System.out.println("-----------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/manager_system?useUnicode=true&characterEncoding=utf-8";
            connection = DriverManager.getConnection(dbUrl, "root", "root");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

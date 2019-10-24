package com.blade.practice.generator;

import com.alibaba.fastjson.JSON;
import com.blade.practice.util.DateUtil;
import com.google.common.base.Charsets;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * @author blade
 * 2019/10/23 16:51
 */
public class Generator {

    public static void main(String[] args) {
        generate();
//        underline2LowerCamelCase("data_id");
    }

    private static String underline2CamelCase(String underlineString, boolean firstWordLowerCase) {
        if (StringUtils.isBlank(underlineString)) {
            return "";
        }

        String[] words = StringUtils.split(underlineString, "_");

        final int length = words.length;
        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i< length; i++) {
            if (0 == i) {
                String firstChar = words[i].substring(0, 1);
                if (firstWordLowerCase) {
                    firstChar = firstChar.toLowerCase();
                    stringBuilder.append(firstChar + words[i].substring(1).toLowerCase());
                }else {
                    firstChar = firstChar.toUpperCase();
                    stringBuilder.append(firstChar + words[i].substring(1).toLowerCase());
                }
            }else {
                String firstChar = words[i].substring(0, 1).toUpperCase();
                stringBuilder.append(firstChar + words[i].substring(1));
            }
        }

        return stringBuilder.toString();
    }

    private static void generate() {
        Table table = getTable("user");

        System.out.println(JSON.toJSONString(table));

        Entity entity = transTable2Entity(table);
        System.out.println(JSON.toJSONString(entity));

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setClassForTemplateLoading(Generator.class, "/template/generator");

        try {
            Template template = configuration.getTemplate("entity.java.ftl");

            String savePath = entity.getSavePath() + StringUtils.replaceAll(entity.getPackagePath(), "\\.",
                    Matcher.quoteReplacement(File.separator));

            if (!savePath.endsWith(File.separator)) {
                savePath += File.separator;
            }

            FileWriter fileWriter = new FileWriter(savePath + entity.getClassName() + ".java");

            template.process(entity, fileWriter);
            fileWriter.flush();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static Entity transTable2Entity(Table table){
        Entity entity = new Entity();

        entity.setPackagePath("com.blade.entity");
        entity.setSavePath("F:\\");

        entity.setAuthor("Blade");
        entity.setClassName(underline2CamelCase(table.getTableName(), false));
        entity.setDescription(table.getRemark());
        entity.setCreateDate(DateUtil.getDateTimeString(DateUtil.getCurrentDateTime()));

        List<String> importClasses = new ArrayList<>();
        List<Property> properties = new ArrayList<>();

        List<Column> columns = table.getColumns();

        for (Column column : columns) {
            Property property = new Property();
            property.setPropertyName(underline2CamelCase(column.getColumnName(), true));
            property.setDescription(column.getRemark());
            property.setMethodName(underline2CamelCase(column.getColumnName(), false));
            property.setJavaType(JdbcTypeToJavaType.jdbcType2JavaType(column.getColumnJdbcType().toUpperCase()));
            properties.add(property);

            String importClass = JdbcTypeToJavaType.getImportClass(column.getColumnJdbcType().toUpperCase());

            if (StringUtils.isNotBlank(importClass)) {
                if (!importClasses.contains(importClass)) {
                    importClasses.add(importClass);
                }
            }
        }

        entity.setProperties(properties);
        entity.setImportClasses(importClasses);

        return entity;
    }

    private static Table getTable(String tableName) {
        Connection connection = getConnection();
        Table table = new Table();
        try {

            ResultSet tableNames = connection.getMetaData().getTables(connection.getCatalog(), "%", tableName, null);

            while (tableNames.next()) {

                String tableName1 = tableNames.getString("TABLE_NAME");
                String remarks = tableNames.getString("REMARKS");
                table.setTableName(tableName1);
                table.setRemark(remarks);
//                System.out.println(tableName + " -- " + remarks);

                List<Column> columns = new ArrayList<>();
                ResultSet columnRs = connection.getMetaData().getColumns(null, null, tableName1, null);
                while (columnRs.next()) {
                    String columnName = columnRs.getString("COLUMN_NAME");
                    String dataType = columnRs.getString("TYPE_NAME");
                    String remark = columnRs.getString("REMARKS");

                    columns.add(new Column(columnName, dataType, remark));
                    table.setColumns(columns);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return table;
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

package com.blade.practice.generator;

import com.blade.practice.util.DateUtil;
import com.blade.practice.util.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * @author blade
 * 2019/10/23 16:51
 */
public class Generator {

    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        TableInfo table = getTable("user");
        table = transTable2Entity(table);

        System.out.println(System.getProperty("user.dir"));

        table.setPackagePath("com.blade.practice.generator");
        table.setSavePath(System.getProperty("user.dir") + File.separator + "blade-practice"
                + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator);

        String savePath = table.getSavePath() + StringUtils.replaceAll(table.getPackagePath(), "\\.",
                Matcher.quoteReplacement(File.separator));
        System.out.println(savePath);
        generateEntity(table, savePath);
        generateMapperXml(table, savePath);
        generateMapper(table, savePath);
        generateService(table, savePath);
        generateServiceImpl(table, savePath);
    }

    private static void generateService(TableInfo table, String savePath) {
        savePath = savePath + File.separator + "service";
        createTemplate(table, savePath, "/template/generator", "service.java.ftl",
                "I" + table.getClassName() + "Service", ".java");
    }

    private static void generateServiceImpl(TableInfo table, String savePath) {
        savePath = savePath + File.separator + "service/impl";
        createTemplate(table, savePath, "/template/generator", "serviceImpl.java.ftl",
                table.getClassName() + "ServiceImpl", ".java");
    }

    private static void generateMapper(TableInfo table, String savePath) {
        savePath = savePath + File.separator + "mapper";

        createTemplate(table, savePath, "/template/generator", "mapper.java.ftl",
                table.getClassName() + "Mapper", ".java");
    }

    private static void generateMapperXml(TableInfo table, String savePath) {
        savePath = savePath + File.separator + "xml";

        createTemplate(table, savePath, "/template/generator", "mapper.xml.ftl",
                table.getClassName() + "Mapper", ".xml");
    }

    private static void generateEntity(TableInfo table, String savePath) {
        savePath = savePath + File.separator + "entity";

        createTemplate(table, savePath, "/template/generator", "entity.java.ftl",
                table.getClassName(), ".java");
    }

    private static void createTemplate(Object o, String savePath, String templatePath, String templateFileName,
                                       String fileName, String fileType) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setClassForTemplateLoading(Generator.class, templatePath);

        try {
            Template template = configuration.getTemplate(templateFileName);

            if (!savePath.endsWith(File.separator)) {
                savePath += File.separator;
            }

            FileUtils.createDir(savePath);

            FileWriter fileWriter = new FileWriter(savePath + fileName + fileType);

            template.process(o, fileWriter);
            fileWriter.flush();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static TableInfo transTable2Entity(TableInfo table) {

        table.setAuthor("Blade");
        table.setClassName(underline2CamelCase(table.getTableName(), false));
        table.setDescription(table.getRemark());
        table.setCreateDate(DateUtil.getDateTimeString(DateUtil.getCurrentDateTime()));

        Set<String> importClasses = new HashSet<>();

        List<TableField> columns = table.getColumns();

        for (TableField column : columns) {
            column.setPropertyName(underline2CamelCase(column.getColumnName(), true));
            column.setMethodName(underline2CamelCase(column.getColumnName(), false));
            column.setJavaType(JdbcTypeToJavaType.jdbcType2JavaType(column.getColumnJdbcType().toUpperCase()));

            String importClass = JdbcTypeToJavaType.getImportClass(column.getColumnJdbcType().toUpperCase());

            if (StringUtils.isNotBlank(importClass)) {
                importClasses.add(importClass);
            }
        }

        table.setImportClasses(importClasses);

        return table;
    }

    private static TableInfo getTable(String tableName) {
        Connection connection = getConnection();
        TableInfo table = new TableInfo();
        try {

            ResultSet tableNames = connection.getMetaData().getTables(connection.getCatalog(), "%", tableName, null);

            while (tableNames.next()) {

                String tableName1 = tableNames.getString("TABLE_NAME");
                String remarks = tableNames.getString("REMARKS");
                table.setTableName(tableName1);
                table.setRemark(remarks);

                List<TableField> columns = new ArrayList<>();
                ResultSet columnRs = connection.getMetaData().getColumns(null, null, tableName1, null);
                while (columnRs.next()) {
                    String columnName = columnRs.getString("COLUMN_NAME");
                    String dataType = columnRs.getString("TYPE_NAME");
                    String remark = columnRs.getString("REMARKS");

                    columns.add(new TableField(columnName, dataType, remark));
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

    private static String underline2CamelCase(String underlineString, boolean firstWordLowerCase) {
        if (StringUtils.isBlank(underlineString)) {
            return "";
        }

        String[] words = StringUtils.split(underlineString, "_");

        final int length = words.length;
        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < length; i++) {
            if (0 == i) {
                String firstChar = words[i].substring(0, 1);
                if (firstWordLowerCase) {
                    firstChar = firstChar.toLowerCase();
                    stringBuilder.append(firstChar + words[i].substring(1).toLowerCase());
                } else {
                    firstChar = firstChar.toUpperCase();
                    stringBuilder.append(firstChar + words[i].substring(1).toLowerCase());
                }
            } else {
                String firstChar = words[i].substring(0, 1).toUpperCase();
                stringBuilder.append(firstChar + words[i].substring(1));
            }
        }

        return stringBuilder.toString();
    }
}

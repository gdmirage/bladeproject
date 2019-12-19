package com.blade.generator.core;

import com.blade.generator.core.config.AbstractDbConfig;
import com.blade.generator.core.config.GenerateFileConfig;
import com.blade.generator.core.config.GlobalConfig;
import com.blade.generator.core.config.PackageConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author blade
 * 2019/10/28 10:26
 */
public class GeneratorInfo {

    private GlobalConfig globalConfig;

    private AbstractDbConfig dbConfig;

    private NamingStrategy namingStrategy;

    private PackageConfig packageConfig;

    private GenerateFileConfig generateFileConfig;

    private List<String> generateTables;

    /**
     * entity 忽略生成的字段, 用 , 分割
     */
    private String entityIgnoreColumn;

    public List<TableInfo> getTableFromDb() {
        List<TableInfo> tableInfoList = new ArrayList<>();

        generateTables.forEach(table -> {
            TableInfo tableInfo = this.getDbTableInfo(table);
            tableInfoList.add(this.assembleGenerateTableInfo(tableInfo));
        });

        return tableInfoList;
    }

    private TableInfo assembleGenerateTableInfo(TableInfo tableInfo) {
        this.generateFileConfig.setEntityName(this.underline2CamelCase(tableInfo.getTableName(), false,
                this.namingStrategy));

        this.setTableInfo(tableInfo);

        Set<String> importClasses = new HashSet<>();

        List<TableField> columns = tableInfo.getColumns();

        for (TableField column : columns) {
            column.setPropertyName(underline2CamelCase(column.getColumnName(), true,
                    this.namingStrategy));
            column.setMethodName(underline2CamelCase(column.getColumnName(), false,
                    this.namingStrategy));
            column.setJavaType(DbTypeMappingEnum.dbType2JavaType(column.getDbType().toUpperCase()));
            column.setJdbcType(DbTypeMappingEnum.dbType2JdbcType(column.getDbType().toUpperCase()));
            System.out.println(column.getColumnName() +"====="+ column.getDbType());
            String importClass = DbTypeMappingEnum.getImportClass(column.getJdbcType().toUpperCase());
            System.out.println(importClass);
            if (StringUtils.isNotBlank(importClass)) {
                importClasses.add(importClass);
            }
        }

        System.out.println(importClasses);

        tableInfo.setImportClasses(importClasses);

        return tableInfo;
    }

    private void setTableInfo(TableInfo tableInfo) {
        tableInfo.setAuthor(this.globalConfig.getAuthor());
        tableInfo.setDescription(tableInfo.getRemark());
        tableInfo.setCreateDate(this.globalConfig.getCreateDate());

        // 生成文件名
        tableInfo.setEntityName(this.generateFileConfig.getEntityName());
        tableInfo.setMapperName(this.generateFileConfig.getMapperName());
        tableInfo.setMapperXmlName(this.generateFileConfig.getMapperXmlName());
        tableInfo.setServiceName(this.generateFileConfig.getServiceName());
        tableInfo.setServiceImplName(this.generateFileConfig.getServiceImplName());
        tableInfo.setControllerName(this.generateFileConfig.getControllerName());

        // 保存路径
        tableInfo.setSavePath(this.packageConfig.getSavePath() + File.separator + this.getPackageConfig().getModule());
        tableInfo.setPackagePath(this.packageConfig.getPackagePath());
        tableInfo.setModule(this.packageConfig.getModule());

        // 生成路径
        tableInfo.setEntityPath(this.packageConfig.getEntityFilePath());
        tableInfo.setMapperPath(this.packageConfig.getMapperFilePath());
        tableInfo.setServicePath(this.packageConfig.getServiceFilePath());
        tableInfo.setServiceImplPath(this.packageConfig.getServiceImplFilePath());
        tableInfo.setControllerPath(this.packageConfig.getControllerFilePath());
        tableInfo.setPageSearchPath(this.packageConfig.getPageSearchFilePath());

        String[] ignoreColumns = StringUtils.split(entityIgnoreColumn, Constants.Symbol.COMMA);
        tableInfo.setEntityIgnoreColumn(Arrays.asList(ignoreColumns));
    }

    private TableInfo getDbTableInfo(String tableName) {
        Connection connection = this.dbConfig.getConnection();
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

            return table;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String underline2CamelCase(String columnName, boolean firstWordLowerCase,
                                              NamingStrategy namingStrategy) {
        switch (namingStrategy) {
            case underline_2_camel:
                return NamingStrategy.underline2Camel(columnName, firstWordLowerCase);
            case no_change:
                return columnName;
            default:
                return NamingStrategy.underline2Camel(columnName, firstWordLowerCase);
        }
    }

    public List<String> getGenerateTables() {
        return generateTables;
    }

    public void setGenerateTables(List<String> generateTables) {
        this.generateTables = generateTables;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public AbstractDbConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(AbstractDbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public NamingStrategy getNamingStrategy() {
        return namingStrategy;
    }

    public void setNamingStrategy(NamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    public PackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    public GenerateFileConfig getGenerateFileConfig() {
        return generateFileConfig;
    }

    public void setGenerateFileConfig(GenerateFileConfig generateFileConfig) {
        this.generateFileConfig = generateFileConfig;
    }

    public void setEntityIgnoreColumn(String entityIgnoreColumn) {
        this.entityIgnoreColumn = entityIgnoreColumn;
    }
}

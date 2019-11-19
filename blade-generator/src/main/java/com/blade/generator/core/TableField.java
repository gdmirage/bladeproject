package com.blade.generator.core;

/**
 * @author blade
 * 2019/10/23 17:33
 */
public class TableField {
    private String columnName;

    /**
     * 数据库类型
     */
    private String dbType;
    private String remark;

    /**
     * 数据库类型映射成jdbcType
     */
    private String jdbcType;

    /**
     * 字段名称
     */
    private String propertyName;

    /**
     * java 类型
     */
    private String javaType;

    /**
     * 方法名  主要是针对get set 方法。第一个单词也要大写
     */
    private String methodName;

    public TableField() {}

    public String getColumnName() {
        return columnName;
    }

    public String getDbType() {
        return dbType;
    }

    public String getRemark() {
        return remark;
    }

    public TableField(String columnName, String dbType, String remark) {
        this.columnName = columnName;
        this.dbType = dbType;
        this.remark = remark;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

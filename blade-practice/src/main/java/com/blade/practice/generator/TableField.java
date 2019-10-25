package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/23 17:33
 */
public class TableField {
    private String columnName;
    private String columnJdbcType;
    private String remark;

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

    public TableField(String columnName, String columnJdbcType, String remark) {
        this.columnName = columnName;
        this.columnJdbcType = columnJdbcType;
        this.remark = remark;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnJdbcType() {
        return columnJdbcType;
    }

    public void setColumnJdbcType(String columnJdbcType) {
        this.columnJdbcType = columnJdbcType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/23 17:33
 */
public class Column {
    private String columnName;
    private String columnJdbcType;

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
}

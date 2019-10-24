package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/23 17:33
 */
public class Column {
    private String columnName;
    private String columnJdbcType;
    private String remark;

    public Column(String columnName, String columnJdbcType, String remark) {
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
}

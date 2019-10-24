package com.blade.practice.generator;

import java.util.List;

/**
 * @author blade
 * 2019/10/23 17:32
 */
public class Table {
    private String tableName;
    private String remark;

    private List<Column> columns;

    public Table() {}

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

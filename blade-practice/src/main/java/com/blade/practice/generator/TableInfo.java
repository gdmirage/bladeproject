package com.blade.practice.generator;

import java.util.List;
import java.util.Set;

/**
 * @author blade
 * 2019/10/23 17:32
 */
public class TableInfo {
    private String savePath;

    private String tableName;
    private String remark;

    private List<TableField> columns;

    /**
     * 作者
     */
    private String author;

    /**
     * 生成时间
     */
    private String createDate;

    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 引入的java类
     */
    private Set<String> importClasses;

    /**
     * 类型
     */
    private String className;

    /**
     * 描述
     */
    private String description;

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

    public List<TableField> getColumns() {
        return columns;
    }

    public void setColumns(List<TableField> columns) {
        this.columns = columns;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public Set<String> getImportClasses() {
        return importClasses;
    }

    public void setImportClasses(Set<String> importClasses) {
        this.importClasses = importClasses;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}

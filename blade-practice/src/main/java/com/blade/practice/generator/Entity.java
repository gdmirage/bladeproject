package com.blade.practice.generator;

import java.util.List;

/**
 * @author blade
 * 2019/10/24 11:06
 */
public class Entity extends BaseFile{
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
    private List<String> importClasses;

    /**
     * 类型
     */
    private String className;

    /**
     * 属性列
     */
    private List<Property> properties;

    /**
     * 描述
     */
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public List<String> getImportClasses() {
        return importClasses;
    }

    public void setImportClasses(List<String> importClasses) {
        this.importClasses = importClasses;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}

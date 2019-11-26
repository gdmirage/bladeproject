package com.blade.generator.core;

import org.apache.commons.lang3.StringUtils;

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
     * 模块
     */
    private String module;

    /**
     * 引入的java类
     */
    private Set<String> importClasses;


    /**
     * 描述
     */
    private String description;

    /**
     * 实体名称
     */
    private String entityName;
    private String mapperName;
    private String serviceName;
    private String serviceImplName;
    private String mapperXmlName;
    private String controllerName;
    private String pageSearchName;

    private String namingService;
    private String namingEntity;

    /**
     * 生成的entity类路径
     */
    private String entityPath;
    private String mapperPath;
    private String servicePath;
    private String serviceImplPath;
    private String controllerPath;
    private String pageSearchPath;

    private String keyColumn = "id";

    public String getPageSearchName() {
        return entityName + "PageSearchDTO";
    }

    public String getNamingEntity() {
        String entityName = this.entityName;
        if (entityName.startsWith("I")) {
            entityName = entityName.substring(1);
        }

        String firstChar = entityName.substring(0, 1);
        return firstChar.toLowerCase() + entityName.substring(1);
    }

    public String getNamingService() {
        String serviceName = this.serviceName;
        if (serviceName.startsWith("I")) {
            serviceName = serviceName.substring(1);
        }

        String firstChar = serviceName.substring(0, 1);
        return firstChar.toLowerCase() + serviceName.substring(1);
    }

    private String getFilePath(String path) {
        String packagePath = this.packagePath;
        String module = this.module;

        if (packagePath.endsWith(Constants.symbol.DOT)) {
            packagePath = packagePath.substring(0, packagePath.length()-1);
        }

        if (StringUtils.isNotBlank(module)) {
            if (module.startsWith(Constants.symbol.DOT)) {
                packagePath += module;
            }else {
                packagePath = packagePath + Constants.symbol.DOT + module;
            }
        }

        if (packagePath.endsWith(Constants.symbol.DOT)) {
            packagePath = packagePath.substring(0, packagePath.length()-1);
        }

        return packagePath + path;
    }

    public String getPageSearchPath() {
        return this.getFilePath(this.pageSearchPath);
    }

    public void setPageSearchPath(String pageSearchPath) {
        this.pageSearchPath = pageSearchPath;
    }

    public String getEntityPath() {
        return this.getFilePath(this.entityPath);
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getMapperPath() {
        return this.getFilePath(this.mapperPath);
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getServicePath() {
        return this.getFilePath(this.servicePath);
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServiceImplPath() {
        return this.getFilePath(this.serviceImplPath);
    }

    public void setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Set<String> getImportClasses() {
        return importClasses;
    }

    public void setImportClasses(Set<String> importClasses) {
        this.importClasses = importClasses;
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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getControllerPath() {
        return this.getFilePath(this.controllerPath);
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }
}

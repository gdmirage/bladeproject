package com.blade.practice.generator;

import org.apache.commons.lang3.StringUtils;

/**
 * @author blade
 * 2019/10/28 11:34
 */
public class GenerateFileConfig {

    private String entityName;
    private String mapperName;
    private String serviceName;
    private String serviceImplName;
    private String mapperXmlName;

    private final String defaultMapperName = this.entityName + "Mapper";
    private final String defaultMapperXmlName = this.entityName + "Mapper";
    private final String defaultServiceName = "I" + this.entityName + "Service";
    private final String defaultServiceImplName = this.entityName + "ServiceImpl";

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMapperName() {
        return this.getString(this.mapperName, defaultMapperName);
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServiceName() {
        return this.getString(this.serviceName, defaultServiceName);
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return this.getString(this.serviceImplName, defaultServiceImplName);
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getMapperXmlName() {
        return this.getString(this.mapperXmlName, defaultMapperXmlName);
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }

    private String getString(String string, String defaultString) {
        return StringUtils.isBlank(string) ? defaultString : string;
    }
}

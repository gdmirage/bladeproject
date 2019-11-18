package com.blade.generator.core;

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

    private final static String DEFAULT_MAPPER_NAME = "Mapper";
    private final static String DEFAULT_MAPPER_XML_NAME = "Mapper";
    private final static String DEFAULT_SERVICE_NAME = "Service";
    private final static String DEFAULT_SERVICE_IMPL_NAME = "ServiceImpl";

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMapperName() {
        return this.getString(this.mapperName, DEFAULT_MAPPER_NAME);
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServiceName() {
        return "I" + this.getString(this.serviceName, DEFAULT_SERVICE_NAME);
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return this.getString(this.serviceImplName, DEFAULT_SERVICE_IMPL_NAME);
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getMapperXmlName() {
        return this.getString(this.mapperXmlName, DEFAULT_MAPPER_XML_NAME);
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }

    private String getString(String string, String defaultString) {
        return StringUtils.isBlank(string) ? this.entityName + defaultString : string;
    }
}

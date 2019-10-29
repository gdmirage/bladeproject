package com.blade.practice.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.regex.Matcher;

/**
 * 代码生成 package 的信息
 *
 * @author blade
 * 2019/10/28 10:51
 */
public class PackageConfig {
    private String packagePath;
    private String mapperPath;
    private String entityPath;
    private String servicePath;
    private String serviceImplPath;
    private String mapperXmlPath;

    private String savePath;

    private static final String defaultMapperPath = File.separator + "mapper";
    private static final String defaultEntityPath = File.separator + "entity";
    private static final String defaultServicePath = File.separator + "service";
    private static final String defaultServiceImplPath = defaultServicePath + File.separator + "impl";
    private static final String defaultMapperXmlPath = File.separator + "xml";

    private static final String defaultMapperFilePath = Constants.symbol.DOT + "mapper";
    private static final String defaultEntityFilePath = Constants.symbol.DOT + "entity";
    private static final String defaultServiceFilePath = Constants.symbol.DOT + "service";
    private static final String defaultServiceImplFilePath = defaultServiceFilePath + Constants.symbol.DOT + "impl";

    public String getMapperFilePath() {
        return defaultMapperFilePath;
    }

    public String getEntityFilePath() {
        return defaultEntityFilePath;
    }

    public String getServiceFilePath() {
        return defaultServiceFilePath;
    }

    public String getServiceImplFilePath() {
        return defaultServiceImplFilePath;
    }

    public String getSavePath() {
        return this.savePath + StringUtils.replaceAll(this.packagePath, "\\.",
                Matcher.quoteReplacement(File.separator));
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getMapperPath() {
        return this.getPath(this.mapperPath, defaultMapperPath);
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getEntityPath() {
        return this.getPath(this.entityPath, defaultEntityPath);
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getServicePath() {
        return this.getPath(this.servicePath, defaultServicePath);
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServiceImplPath() {
        return this.getPath(this.serviceImplPath, defaultServiceImplPath);
    }

    public void setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
    }

    public String getMapperXmlPath() {
        return this.getPath(this.mapperXmlPath, defaultMapperXmlPath);
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    private String getPath(String path, String defaultPath) {
        return StringUtils.isBlank(path) ? defaultPath : path;
    }
}

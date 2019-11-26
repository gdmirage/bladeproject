package com.blade.generator.core.config;

import com.blade.generator.core.Constants;
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
    private String controllerPath;
    private String pageSearchPath;

    private String savePath;
    private String module;

    private static final String defaultMapperPath = File.separator + "mapper";
    private static final String defaultEntityPath = File.separator + "entity";
    private static final String defaultServicePath = File.separator + "service";
    private static final String defaultServiceImplPath = defaultServicePath + File.separator + "impl";
    private static final String defaultMapperXmlPath = File.separator + "xml";
    private static final String defaultControllerPath = File.separator + "controller";
    private static final String defaultPageSearchPath = File.separator + "model";

    private static final String defaultMapperFilePath = Constants.Symbol.DOT + "mapper";
    private static final String defaultEntityFilePath = Constants.Symbol.DOT + "entity";
    private static final String defaultServiceFilePath = Constants.Symbol.DOT + "service";
    private static final String defaultServiceImplFilePath = defaultServiceFilePath + Constants.Symbol.DOT + "impl";
    private static final String defaultControllerFilePath = Constants.Symbol.DOT + "controller";
    private static final String defaultPageSearchFilePath = Constants.Symbol.DOT + "model";

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

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

    public String getControllerFilePath() {
        return defaultControllerFilePath;
    }

    public String getPageSearchFilePath() {
        return defaultPageSearchFilePath;
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

    public String getControllerPath() {
        return this.getPath(this.controllerPath, defaultControllerPath);
    }

    public String getPageSearchPath() {
        return this.getPath(this.pageSearchPath, defaultPageSearchPath);
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    private String getPath(String path, String defaultPath) {
        return StringUtils.isBlank(path) ? defaultPath : path;
    }
}

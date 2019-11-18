package com.blade.generator.core.generators;

import com.blade.generator.core.Constants;
import com.blade.generator.core.GeneratorInfo;
import com.blade.generator.core.TableInfo;
import com.blade.generator.util.FreeMarkerUtil;

/**
 * @author blade
 * 2019/10/28 15:06
 */
public class ServiceGenerator extends AbstractGenerator {
    private final String serviceTemplateName = "service.java.ftl";
    private final String serviceImplTemplateName = "serviceImpl.java.ftl";

    public ServiceGenerator(IGenerator generator, TableInfo tableInfo, GeneratorInfo generatorInfo) {
        super(generator, tableInfo, generatorInfo);
    }

    @Override
    public void generate() {
        super.getGenerator().generate();
        this.generateFile();
    }

    @Override
    void generateFile() {
        System.out.println("generating service and serviceImpl");
        this.generateService();
        this.generateServiceImpl();
    }

    private void generateService() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.serviceTemplateName,
                this.getServiceFileSavePath(), this.getServiceFileName());
    }

    private String getServiceFileName() {
        return this.getTableInfo().getServiceName() + Constants.DOT_JAVA;
    }

    private String getServiceFileSavePath() {
        return this.getTableInfo().getSavePath() + this.getGeneratorInfo().getPackageConfig().getServicePath();
    }

    private void generateServiceImpl() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.serviceImplTemplateName,
                this.getServiceImplFileSavePath(), this.getServiceImplFileName());
    }

    private String getServiceImplFileName() {
        return this.getTableInfo().getServiceImplName() + Constants.DOT_JAVA;
    }

    private String getServiceImplFileSavePath() {
        return this.getTableInfo().getSavePath() + this.getGeneratorInfo().getPackageConfig().getServiceImplPath();
    }
}

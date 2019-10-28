package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/28 15:06
 */
public class ServiceGenerator extends AbstractGenerator {
    private final String serviceTemplateName = "service.java.ftl";
    private final String serviceImplTemplateName = "serviceImpl.java.ftl";

    public ServiceGenerator(IGenerator generator, TableInfo tableInfo) {
        super(generator, tableInfo);
    }

    @Override
    public void generate() {
        super.getGenerator().generate();
        this.generateFile();
    }

    @Override
    void generateFile() {
        System.out.println("generating service and serviceImpl");
    }
}

package com.blade.practice.generator;

import com.blade.practice.util.FreeMarkerUtil;

/**
 * @author blade
 * 2019/10/28 15:04
 */
public class MapperGenerator extends AbstractGenerator {
    private final String mapperTemplateName = "mapper.java.ftl";
    private final String mapperXmlTemplateName = "mapper.xml.ftl";

    public MapperGenerator(IGenerator generator, TableInfo tableInfo, GeneratorInfo generatorInfo) {
        super(generator, tableInfo, generatorInfo);
    }

    @Override
    public void generate() {
        super.getGenerator().generate();
        this.generateFile();
    }

    @Override
    void generateFile() {
        System.out.println("generating mapper and mapper.xml");
        this.generateMapper();
        this.generateMapperXml();
    }

    private void generateMapper() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.mapperTemplateName,
                this.getMapperFileSavePath(), this.getMapperFileName());
    }

    private String getMapperFileName() {
        return this.getTableInfo().getMapperName() + Constants.DOT_JAVA;
    }

    private String getMapperFileSavePath() {
        return this.getTableInfo().getSavePath() + this.getGeneratorInfo().getPackageConfig().getMapperPath();
    }


    private void generateMapperXml() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.mapperXmlTemplateName,
                this.getMapperXmlFileSavePath(), this.getMapperXmlFileName());
    }

    private String getMapperXmlFileName() {
        return this.getTableInfo().getMapperXmlName() + Constants.DOT_XML;
    }

    private String getMapperXmlFileSavePath() {
        return this.getTableInfo().getSavePath() + this.getGeneratorInfo().getPackageConfig().getMapperXmlPath();
    }
}

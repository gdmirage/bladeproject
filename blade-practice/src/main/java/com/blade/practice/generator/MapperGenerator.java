package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/28 15:04
 */
public class MapperGenerator extends AbstractGenerator {
    private final String mapperTemplateName = "mapper.java.ftl";
    private final String mapperXmlTemplateName = "mapper.xml.ftl";

    public MapperGenerator(IGenerator generator, TableInfo tableInfo) {
        super(generator, tableInfo);
    }

    @Override
    public void generate() {
        super.getGenerator().generate();
        this.generateFile();
    }

    @Override
    void generateFile() {
        System.out.println("generating mapper and mapper.xml");
    }
}

package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/28 15:02
 */
public class EntityGenerator implements IGenerator {
    private final String entityTemplateName = "entity.java.ftl";

    private TableInfo tableInfo;

    public EntityGenerator(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    @Override
    public void generate() {
        this.generateFile();
    }

    private void generateFile() {
        System.out.println("generating entity");
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }
}

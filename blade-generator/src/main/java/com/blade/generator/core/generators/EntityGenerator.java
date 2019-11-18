package com.blade.generator.core.generators;

import com.blade.generator.core.Constants;
import com.blade.generator.core.GeneratorInfo;
import com.blade.generator.core.TableInfo;
import com.blade.generator.util.FreeMarkerUtil;

/**
 * @author blade
 * 2019/10/28 15:02
 */
public class EntityGenerator implements IGenerator {
    private final String entityTemplateName = "entity.java.ftl";

    private TableInfo tableInfo;

    private GeneratorInfo generatorInfo;

    public EntityGenerator(TableInfo tableInfo, GeneratorInfo generatorInfo) {
        this.tableInfo = tableInfo;
        this.generatorInfo = generatorInfo;
    }

    @Override
    public void generate() {
        this.generateFile();
    }

    private void generateFile() {
        System.out.println("generating entity");
        FreeMarkerUtil.createFile(this.tableInfo, Constants.TEMPLATE_PATH, this.entityTemplateName,
                this.getFileSavePath(), this.getFileName());
    }

    private String getFileName() {
        return this.tableInfo.getEntityName() + Constants.DOT_JAVA;
    }

    private String getFileSavePath() {
        return this.tableInfo.getSavePath() + this.generatorInfo.getPackageConfig().getEntityPath();
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }
}

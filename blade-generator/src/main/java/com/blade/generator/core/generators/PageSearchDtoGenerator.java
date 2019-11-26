package com.blade.generator.core.generators;

import com.blade.generator.core.Constants;
import com.blade.generator.core.GeneratorInfo;
import com.blade.generator.core.TableInfo;
import com.blade.generator.util.FreeMarkerUtil;

/**
 * @author blade
 * 2019/10/28 15:08
 */
public class PageSearchDtoGenerator extends AbstractGenerator {

    private final String pageSearchDTOTemplateName = "pageSearchDTO.java.ftl";

    public PageSearchDtoGenerator(IGenerator generator, TableInfo tableInfo, GeneratorInfo generatorInfo) {
        super(generator, tableInfo, generatorInfo);
    }

    @Override
    public void generate() {
        super.getGenerator().generate();
        this.generateFile();
    }

    @Override
    void generateFile() {
        System.out.println("generating page search dto");
        this.generatePageSearch();
    }

    private void generatePageSearch() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.pageSearchDTOTemplateName,
                this.getPageSearchDtoFileSavePath(), this.getPageSearchDtoFileName());
    }

    private String getPageSearchDtoFileName() {
        return this.getTableInfo().getPageSearchName() + Constants.DOT_JAVA;
    }

    private String getPageSearchDtoFileSavePath() {
        return this.getTableInfo().getSavePath() + this.getGeneratorInfo().getPackageConfig().getPageSearchPath();
    }
}

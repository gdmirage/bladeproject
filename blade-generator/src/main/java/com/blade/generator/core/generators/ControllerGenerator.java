package com.blade.generator.core.generators;

import com.blade.generator.core.Constants;
import com.blade.generator.core.GeneratorInfo;
import com.blade.generator.core.TableInfo;
import com.blade.generator.util.FreeMarkerUtil;

import java.io.File;

/**
 * @author blade
 * 2019/10/28 15:08
 */
public class ControllerGenerator extends AbstractGenerator {

    private final String apiControllerTemplateName = "apiController.java.ftl";

    private final String controllerTemplateName = "controller.java.ftl";

    public ControllerGenerator(IGenerator generator, TableInfo tableInfo, GeneratorInfo generatorInfo) {
        super(generator, tableInfo, generatorInfo);
    }

    @Override
    public void generate() {
        super.getGenerator().generate();
        this.generateFile();
    }

    @Override
    void generateFile() {
        System.out.println("generating controller");
        this.generateApiController();
        this.generateController();
    }

    private void generateController() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.controllerTemplateName,
                this.getControllerFileSavePath(), this.getControllerFileName());
    }

    private void generateApiController() {
        FreeMarkerUtil.createFile(super.getTableInfo(), Constants.TEMPLATE_PATH, this.apiControllerTemplateName,
                this.getApiControllerFileSavePath(), this.getControllerFileName());
    }

    private String getControllerFileName() {
        return this.getTableInfo().getControllerName() + Constants.DOT_JAVA;
    }

    private String getApiControllerFileSavePath() {
        return this.getTableInfo().getSavePath() + File.separator + "api" + File.separator
                + this.getGeneratorInfo().getPackageConfig().getControllerPath();
    }

    private String getControllerFileSavePath() {
        return this.getTableInfo().getSavePath() + this.getGeneratorInfo().getPackageConfig().getControllerPath();
    }
}

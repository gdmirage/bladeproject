package com.blade.practice.generator.generators;

import com.blade.practice.generator.GeneratorInfo;
import com.blade.practice.generator.TableInfo;

/**
 * @author blade
 * 2019/10/28 15:08
 */
public class ControllerGenerator extends AbstractGenerator {
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
    }
}
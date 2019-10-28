package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/28 15:08
 */
public class ControllerGenerator extends AbstractGenerator {
    public ControllerGenerator(IGenerator generator, TableInfo tableInfo) {
        super(generator, tableInfo);
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

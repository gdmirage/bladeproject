package com.blade.practice.generator.generators;

import com.blade.practice.generator.GeneratorInfo;
import com.blade.practice.generator.TableInfo;

/**
 * 抽象生成器
 *
 * @author blade
 * 2019/10/28 14:59
 */
public abstract class AbstractGenerator implements IGenerator {

    private IGenerator generator;

    private TableInfo tableInfo;

    private GeneratorInfo generatorInfo;

    public AbstractGenerator(IGenerator generator, TableInfo tableInfo, GeneratorInfo generatorInfo) {
        this.generator = generator;
        this.tableInfo =tableInfo;
        this.generatorInfo = generatorInfo;
    }

    /**
     * 生成文件
     */
    abstract void generateFile();

    public IGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(IGenerator generator) {
        this.generator = generator;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public GeneratorInfo getGeneratorInfo() {
        return generatorInfo;
    }

    public void setGeneratorInfo(GeneratorInfo generatorInfo) {
        this.generatorInfo = generatorInfo;
    }
}

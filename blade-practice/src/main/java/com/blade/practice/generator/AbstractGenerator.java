package com.blade.practice.generator;

/**
 * 抽象生成器
 *
 * @author blade
 * 2019/10/28 14:59
 */
public abstract class AbstractGenerator implements IGenerator {

    private IGenerator generator;

    private TableInfo tableInfo;

    public AbstractGenerator(IGenerator generator, TableInfo tableInfo) {
        this.generator = generator;
        this.tableInfo =tableInfo;
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
}

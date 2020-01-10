package com.blade.core.excel;

import java.io.File;

/**
 * @author blade
 * 2019/9/3 10:33
 */
public abstract class AbstractReportExcelExport<T> extends AbstractExcelExport<T> {

    /**
     * 更新数据库操作
     *
     * @param saveExcel 生成并保存的Excel文件
     * @param t         {@link T}
     */
    protected abstract void updateDbData(File saveExcel, T t);

    @Override
    public File exportExcel(T t) {

        File saveExcel = super.exportExcel(t);

        this.updateDbData(saveExcel, t);

        return null;
    }
}

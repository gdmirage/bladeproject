package com.blade.core.excel;

import java.io.File;

/**
 * excel 导出服务
 *
 * @author blade
 * 2019/8/30 15:32
 */
public interface IExcelExportService<T> {

    /**
     * 导出 Excel
     *
     * @return {@link File}
     */
    File exportExcel(T t);
}

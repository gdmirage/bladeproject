package com.blade.core.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 填充 Excel 数据内容接口
 *
 * @author blade
 * 2019/8/30 15:25
 */
public interface IExcelDataService<T> {

    /**
     * 填写 Excel 数据
     *
     * @param xssfWorkbook {@link XSSFWorkbook}
     * @param t            泛型
     * @return {@link XSSFWorkbook}
     */
    XSSFWorkbook writeExcelData(XSSFWorkbook xssfWorkbook, T t);

    /**
     * 获取查询条件的数据
     *
     * @return 查询结果的数据
     */
    int getDataNum();
}

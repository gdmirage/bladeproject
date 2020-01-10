package com.blade.core.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

/**
 * 保存 Excel 文件接口
 *
 * @author blade
 * 2019/8/30 15:26
 */
public interface IExcelSaveService {

    /**
     * 保存Excel
     *
     * @param xssfWorkbook {@link XSSFWorkbook}
     * @param savePath     保存路径
     * @param fileName     文件名
     * @return {@link File}
     * @throws Exception 异常
     */
    File saveExcel(XSSFWorkbook xssfWorkbook, String savePath, String fileName) throws Exception;
}

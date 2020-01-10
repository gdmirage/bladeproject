package com.blade.core.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

/**
 * 获取 Excel 模板接口
 *
 * @author blade
 * 2019/8/30 15:25
 */
public interface IExcelTemplateService {

    /**
     * 获取获取Excel，主要是把报表的title定义清楚
     * 目前只实现 xls 的Excel，后续可以继续扩展
     *
     * @param titleList         excel 的title，与 excelTemplatePath 互斥
     * @param excelTemplatePath Excel 的模板路径，必须在resources下面
     * @return {@link XSSFWorkbook}
     * @throws IOException 异常
     */
    XSSFWorkbook getExcelTemplate(List<String> titleList, String excelTemplatePath) throws IOException;
}

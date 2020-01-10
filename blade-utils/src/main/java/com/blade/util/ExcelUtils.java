package com.blade.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel 工具类
 *
 * @author lql
 * <p>
 * 2019年7月20日-下午2:55:21
 */
public class ExcelUtils {

    public static void main(String[] args) throws Exception {
        List<String> title = new ArrayList<>();
        title.add("title1");
        title.add("title2");
        title.add("title3");
        title.add("title4");

        XSSFWorkbook workbook = getExcel(title);

        FileUtils.createFileWithDatePath("D:\\", "test.xlsx", workbook);
    }

    /**
     * 获得指定的Excel文件
     *
     * @param excelFilePath Excel文件路径
     * @return {@link HSSFWorkbook}
     * @throws Exception 异常
     */
    public static HSSFWorkbook getExcel(String excelFilePath) throws Exception {
        return new HSSFWorkbook(new FileInputStream(excelFilePath));
    }

    /**
     * 获得resource 下的Excel文件
     *
     * @param inputStream {@link InputStream}
     * @return {@link XSSFWorkbook}
     * @throws IOException 异常
     */
    public static XSSFWorkbook getExcel(InputStream inputStream) throws IOException {
        return new XSSFWorkbook(inputStream);
    }

    /**
     * 根据Excel表头创建空模板Excel
     *
     * @param titleList Excel 表头
     * @return {@link HSSFWorkbook}
     */
    public static XSSFWorkbook getExcel(List<String> titleList) {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("sheet1");
        XSSFRow xssfRow = xssfSheet.createRow(0);

        for (int i = 0; i < titleList.size(); i++) {
            CellUtil.createCell(xssfRow, i, titleList.get(i)).setCellStyle(getBorderStyle(xssfWorkbook));
        }
        return xssfWorkbook;
    }

    public static CellStyle getBorderStyle(XSSFWorkbook hssfWorkbook) {
        XSSFCellStyle style = hssfWorkbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
}

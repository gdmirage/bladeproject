package com.blade.core.excel;

import com.blade.util.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author blade
 * 2019/9/2 10:47
 */
@Service("excelSaveService")
public class ExcelSaveServiceImpl implements IExcelSaveService {
    @Override
    public File saveExcel(XSSFWorkbook workbook, String savePath, String fileName) throws Exception {
        return FileUtils.createFileWithDatePath(savePath, fileName, workbook);
    }
}

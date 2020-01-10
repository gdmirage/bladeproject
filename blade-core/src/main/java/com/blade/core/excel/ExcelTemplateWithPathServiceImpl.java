package com.blade.core.excel;

import com.blade.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 根据文件路径获取Excel模板
 *
 * @author blade
 * 2019/8/30 15:56
 */
@Service("excelTemplateWithPathService")
public class ExcelTemplateWithPathServiceImpl implements IExcelTemplateService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public XSSFWorkbook getExcelTemplate(List<String> title, String excelTemplatePath) throws IOException {

        if (StringUtils.isEmpty(excelTemplatePath)) {
            logger.error("获取Excel模板的路径为空");
            return null;
        }

        logger.info("根据模板路径获取Excel模板");

        return ExcelUtils.getExcel(this.getClass().getResourceAsStream(excelTemplatePath));
    }
}

package com.blade.core.excel;

import com.blade.util.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author blade
 * 2019/8/30 16:03
 */
@Service("excelTemplateWithTitleService")
public class ExcelTemplateWithTitleServiceImpl implements IExcelTemplateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public XSSFWorkbook getExcelTemplate(List<String> titleList, String excelTemplatePath) throws IOException {

        if (CollectionUtils.isEmpty(titleList)) {
            logger.error("无Excel表头信息");
            return null;
        }

        logger.info("根据表头创建Excel模板");
        return ExcelUtils.getExcel(titleList);
    }
}

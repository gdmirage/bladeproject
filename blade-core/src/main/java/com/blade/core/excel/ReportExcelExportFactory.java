package com.blade.core.excel;

import com.blade.core.util.spring.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author blade
 * 2019/9/3 10:48
 */
@Component
public class ReportExcelExportFactory {

    private final String excel = "ExcelService";

    @Autowired
    private ApplicationContextProvider provider;
    
    public AbstractReportExcelExport getInstance(String excelType) {
        return provider.getApplicationContext().getBeansOfType(AbstractReportExcelExport.class).get(excelType + excel);
    }
}

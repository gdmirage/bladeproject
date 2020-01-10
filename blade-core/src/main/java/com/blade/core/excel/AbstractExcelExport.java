package com.blade.core.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author blade
 * 2019/8/30 16:14
 */
public abstract class AbstractExcelExport<T> implements IExcelExportService<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected IExcelTemplateService excelTemplateService;

    protected IExcelDataService<T> excelDataService;

    protected IExcelSaveService excelSaveService;

    protected List<String> titleList;

    protected String excelTemplatePath;

    protected String saveExcelPath;

    protected String fileName;

    /**
     * 设置生成模板的的服务
     */
    protected abstract void setExcelTemplateService();

    /**
     * 设置生成 excel 数据的服务
     */
    protected abstract void setExcelDataService();

    /**
     * 设置保存Excel文件的服务
     */
    protected abstract void setExcelSaveService();

    /**
     * 设置表头信息
     * @param t T
     */
    protected abstract void setTitleList(T t);

    /**
     * 设置Excel模板路径
     */
    protected abstract void setExcelTemplatePath();

    /**
     * 设置Excel保存路径
     */
    protected abstract void setSaveExcelPath();

    /**
     * 设置文件名信息
     * @param t T
     */
    protected abstract void setFileName(T t);

    private void init(T t) {
        this.setExcelTemplateService();
        this.setExcelDataService();
        this.setExcelSaveService();
        this.setExcelTemplatePath();
        this.setSaveExcelPath();
        this.setFileName(t);
        this.setTitleList(t);
        logger.debug("初始化完Excel导出的数据");
    }

    @Override
    public File exportExcel(T t) {
        init(t);

        if (!this.validate()) {
            logger.info("参数校验不通过，直接放");
            return null;
        }
        XSSFWorkbook excelTemplate = null;
        try {
            excelTemplate = this.excelTemplateService.getExcelTemplate(titleList, excelTemplatePath);
        } catch (IOException e) {
            logger.error("获取Excel模板异常", e);
            return null;
        }
        XSSFWorkbook saveExcelFile = this.excelDataService.writeExcelData(excelTemplate, t);

        try {
            return this.excelSaveService.saveExcel(saveExcelFile, this.saveExcelPath, this.fileName);
        } catch (Exception e) {
            logger.error("生成Excel报表异常", e);
            return null;
        }
    }

    /**
     * 校验参数
     *
     * @return boolean
     */
    private boolean validate() {
        logger.debug("开始校验Excel导出的数据");
        if (null == this.excelTemplateService) {
            logger.info("获取excel模板的服务获取不到");
            return false;
        }

        if (null == this.excelDataService) {
            logger.info("excel数据的服务获取不到");
            return false;
        }

        if (null == this.excelSaveService) {
            logger.info("保存excel的服务获取不到");
            return false;
        }

        if (StringUtils.isEmpty(this.excelTemplatePath) && CollectionUtils.isEmpty(this.titleList)) {
            logger.info("Excel模板路径与Excel 标题列 不能同时为空");
            return false;
        }


        if (StringUtils.isEmpty(this.fileName)) {
            logger.info("Excel 保存的文件名不能为空");
            return false;
        }

        if (StringUtils.isEmpty(this.saveExcelPath)) {
            logger.info("Excel 保存的路径不能为空");
            return false;
        }
        logger.debug("结束校验Excel导出的数据");
        return true;
    }

}

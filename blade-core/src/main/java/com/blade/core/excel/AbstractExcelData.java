package com.blade.core.excel;

import com.blade.core.model.base.JsonAble;
import com.blade.util.ExcelUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author blade
 * 2019/8/30 17:36
 */
public abstract class AbstractExcelData<T, DATA> extends JsonAble implements IExcelDataService<T> {

    private static final long serialVersionUID = -5764727723840685917L;

    protected List<DATA> dataList;

    protected int startRowNum = 0;

    protected List<String> excelHeaders;

    protected CellStyle style;

    /**
     * 获取写入Excel 的数据
     *
     * @param searchModel 查询的条件
     */
    protected abstract void getExcelData(T searchModel);

    /**
     * 设置数据初始行
     */
    protected abstract void setStartRowNum();

    /**
     * 设置表头
     *
     * @param searchModel T
     */
    protected abstract void setExcelHeaders(T searchModel);

    private void setCellStyle(XSSFWorkbook xssfWorkbook) {
        this.style = ExcelUtils.getBorderStyle(xssfWorkbook);
    }

    private void init(XSSFWorkbook xssfWorkbook, T searchModel) {
        // 获取Excel 数据
        this.getExcelData(searchModel);

        // 设置 Excel 表头
        this.setExcelHeaders(searchModel);
        // 设置 单元格格式
        this.setCellStyle(xssfWorkbook);
    }

    @Override
    public XSSFWorkbook writeExcelData(XSSFWorkbook xssfWorkbook, T searchModel) {

        // 初始化数据
        this.init(xssfWorkbook, searchModel);

        if (CollectionUtils.isEmpty(this.dataList)) {
            return xssfWorkbook;
        }

        // 组装报表数据
        this.assembleData(xssfWorkbook);

        return xssfWorkbook;
    }

    private void assembleData(XSSFWorkbook excelTemplate) {
        if (CollectionUtils.isEmpty(this.dataList)) {
            logger.info("无报表数据");
            return;
        }

        XSSFSheet sheet = excelTemplate.getSheetAt(0);

        int startRowNum = 1;

        for (int i = 0; i < this.dataList.size(); i++) {
            this.assembleRowData(sheet.createRow(startRowNum + i), dataList.get(i));
        }
    }

    /**
     * 组装行数据
     *
     * @param row  Excel row
     * @param data data
     */
    protected abstract void assembleRowData(XSSFRow row, DATA data);

    @Override
    public int getDataNum() {
        return this.dataList.size();
    }


    protected String objectToString(Object o) {
        if ((o instanceof List)) {
            List<?> list = (List) o;
            if (!CollectionUtils.isEmpty(list)) {
                StringBuilder stringBuilder = new StringBuilder();
                list.forEach(list1 -> {
                    stringBuilder.append(list1).append(",");
                });

                String str = stringBuilder.toString();
                if (str.length() > 1) {
                    str = str.substring(0, str.length() - 1);
                }

                return str;
            }
        }

        if (null != o) {
            return o.toString();
        }

        return "";
    }
}

package com.blade.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @author blade
 * 2019/10/25 10:14
 */
public class FileUtils {

    public static void main(String[] args) {
        String path = "F:\\a\\b\\c\\d";
        createDir(path);
    }

    public static void createDir(String path) {
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 创建带有日期目录的文件
     *
     * @param dir      创建路径
     * @param fileName 文件名
     * @param worker   excel文件
     * @return {@link File}
     * @throws Exception 异常
     */
    public static File createFileWithDatePath(String dir, String fileName, XSSFWorkbook worker) throws Exception {
        FileOutputStream fos = null;
        try {

            String dateDir = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATTER_1);

            dir = dir + File.separator + dateDir;

            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                boolean success = dirFile.mkdirs();
                if (!success) {
                    throw new RuntimeException("fail to create dir");
                }
            }
            File excelFile = new File(dirFile.getAbsolutePath() + File.separator + fileName);
            if (!excelFile.exists()) {
                excelFile.createNewFile();
            }
            fos = new FileOutputStream(excelFile);
            worker.write(fos);
            fos.close();
            return excelFile;
        } catch (Exception e) {
            throw e;
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

    }
}

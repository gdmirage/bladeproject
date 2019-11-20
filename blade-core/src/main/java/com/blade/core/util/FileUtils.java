package com.blade.core.util;

import java.io.File;

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
}

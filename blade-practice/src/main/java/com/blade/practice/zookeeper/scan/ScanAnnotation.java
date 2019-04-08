package com.blade.practice.zookeeper.scan;

import com.blade.practice.zookeeper.PropertiesUtils;
import com.blade.practice.zookeeper.annotation.Value;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/11/23 16:25
 */
public class ScanAnnotation {

    private static String SCAN_PACKAGE = PropertiesUtils.getConfigValue("scan.package");

    private static String CLASS_SUFFIX = ".class";

    public static void scan() {
        String basePath = ScanAnnotation.class.getClass().getResource("/").getPath();
        String scanPath = basePath + SCAN_PACKAGE.replaceAll("\\.", "/");

        List<Class<?>> classList = loadAllClasses(SCAN_PACKAGE, scanPath);
        scanAnnotation(classList);
    }

    private static void scanAnnotation(List<Class<?>> classList) {
        for (Class<?> clazz : classList) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Value value = field.getAnnotation(Value.class);

                if (null == value) {
                    continue;
                }
                String key = value.value();

                if ("".equals(key)) {
                    continue;
                }
//                System.out.println("key=========" + key);
                String propertyValue = PropertiesUtils.getConfigValue(key);

                field.setAccessible(true);
//                System.out.println("propertyValue==========" + propertyValue);
                try {
                    System.out.println(field.getType());
                    Class<?> fieldType = field.getType();
                    if (fieldType == String.class) {
                        field.set(field, propertyValue);
                    } else if (fieldType == int.class) {
                        field.setInt(field, Integer.parseInt(propertyValue));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String basePath = ScanAnnotation.class.getClass().getResource("/").getPath();
        String scanPath = basePath + SCAN_PACKAGE.replaceAll("\\.", "/");

        List<Class<?>> classList = loadAllClasses(SCAN_PACKAGE, scanPath);
        System.out.println(classList);
        scanAnnotation(classList);

    }

    private static List<Class<?>> loadAllClasses(String packageName, String path) {
        List<Class<?>> classList = new ArrayList<>();

        System.out.println("packageName : " + packageName + "。  path : " + path);

        File file = new File(path);
        String filePath = file.getPath();
        if (!file.isDirectory()) {

            String fileName = file.getName().replace(CLASS_SUFFIX, "");
            String classpath = packageName + "." + fileName;
            Class<?> clazz = getClass(classpath);
            if (null != clazz) {
                classList.add(clazz);
            }
        } else {
            File[] files = file.listFiles();

            if (null == files) {
                return classList;
            }

            for (File scanFile : files) {
                if (scanFile.isDirectory()) {
//                    System.out.println("file==="+file.getPath());
//                    System.out.println("file1==="+scanFile.getPath());
                    String file1Path = scanFile.getPath();
                    if (!filePath.equals(file1Path)) {
                        String newPath = path + "/" + scanFile.getName();
                        String newPackageName = packageName + "." + scanFile.getName();
                        classList.addAll(loadAllClasses(newPackageName, newPath));
                    }

                } else {
                    String fileName = scanFile.getName().replace(CLASS_SUFFIX, "");
                    String classpath = packageName + "." + fileName;
                    Class<?> clazz = getClass(classpath);
                    if (null != clazz) {
                        classList.add(clazz);
                    }
                }
            }
        }

        return classList;
    }

    private static Class<?> getClass(String classpath) {
        try {
            Class<?> clazz = Class.forName(classpath);
            System.out.println(clazz);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

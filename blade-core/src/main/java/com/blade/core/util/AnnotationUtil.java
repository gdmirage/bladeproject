package com.blade.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解工具类
 *
 * @author blade
 * 2019/11/28 15:37
 */
public class AnnotationUtil {

    /**
     * 判断该类的字段 是否有 指定 注解
     *
     * @param annotationClass annotation class
     * @param judgeClass      judge class
     * @return boolean
     */
    public static boolean hasAnnotation(Class annotationClass, Class judgeClass) {

        if (classHasAnnotation(annotationClass, judgeClass)) {
            return true;
        } else if (fieldHasAnnotation(annotationClass, judgeClass)) {
            return true;
        } else if (methodHasAnnotation(annotationClass, judgeClass)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断该类是否有 类级别的 注解
     *
     * @param annotationClass annotation class
     * @param judgeClass      judge class
     * @return boolean
     */
    public static boolean classHasAnnotation(Class annotationClass, Class judgeClass) {
        Object o = judgeClass.getAnnotation(annotationClass);
        return null != o;
    }

    /**
     * 判断该类的字段 是否有 字段级别的 注解
     *
     * @param annotationClass annotation class
     * @param judgeClass      judge class
     * @return boolean
     */
    public static boolean fieldHasAnnotation(Class annotationClass, Class judgeClass) {
        Field[] fields = judgeClass.getDeclaredFields();

        for (Field field : fields) {
            Object o = field.getAnnotation(annotationClass);
            if (null != o) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断该类的方法 是否有 方法级别的 注解
     *
     * @param annotationClass annotation class
     * @param judgeClass      judge class
     * @return boolean
     */
    public static boolean methodHasAnnotation(Class annotationClass, Class judgeClass) {
        Method[] methods = judgeClass.getDeclaredMethods();
        for (Method method : methods) {
            Object o = method.getAnnotation(annotationClass);
            if (null != o) {
                return true;
            }
        }

        return false;
    }
}

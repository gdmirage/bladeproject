package com.blade.generator.core;

import org.apache.commons.lang3.StringUtils;

/**
 * 表字段转成java bean 命名策略
 *
 * @author blade
 * 2019/10/28 10:59
 */
public enum NamingStrategy {

    /**
     * 不改变
     */
    no_change,

    /**
     * 下划线转驼峰
     */
    underline_2_camel;

    NamingStrategy() {
    }

    /**
     * 字段名 下划线转驼峰标识
     *
     * @param columnName         表字段名
     * @param firstWordLowerCase 第一个单词是否以小写开头
     * @return String
     */
    public static String underline2Camel(String columnName, boolean firstWordLowerCase) {
        if (StringUtils.isBlank(columnName)) {
            return "";
        }

        String[] words = StringUtils.split(columnName, "_");

        final int length = words.length;
        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < length; i++) {
            if (0 == i) {
                if (firstWordLowerCase) {
                    stringBuilder.append(firstCharLower(words[i]));
                } else {
                    stringBuilder.append(firstCharUpper(words[i]));
                }
            } else {
                stringBuilder.append(firstCharUpper(words[i]));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 首字母小写，其余的不变
     *
     * @param word 需要转换的单词
     * @return String
     */
    private static String firstCharLower(String word) {
        String firstChar = word.substring(0, 1);
        return firstChar.toLowerCase() + word.substring(1);
    }

    /**
     * 首字母大写， 其余的不变
     *
     * @param word 需要转换的单词
     * @return String
     */
    private static String firstCharUpper(String word) {
        String firstChar = word.substring(0, 1);
        return firstChar.toUpperCase() + word.substring(1);
    }
}

package com.blade.util.enums;

import java.util.Objects;

/**
 * TODO:
 * 日期时间格式枚举
 *
 * @author Blade
 * @date 2020/5/8 8:52
 */
public enum DateTimePatternEnum {
    /**
     * 日期时间格式枚举
     */
    DATE_FORMAT_1("dateFormat1", "yyyy-MM-dd"),
    DATE_FORMAT_2("dateFormat2", "yyyyMMdd"),
    DATE_FORMAT_3("dateFormat3", "yyyy/MM/dd"),
    TIME_FORMAT_1("timeFormat1", "HHmmss"),
    TIME_FORMAT_2("timeFormat2", "HH:mm:ss"),
    DATE_TIME_FORMAT_1("datetimeFormat1", "yyyy-MM-dd HH:mm:ss"),
    ;

    private String formatKey;
    private String pattern;

    DateTimePatternEnum(String formatKey, String pattern) {
        this.formatKey = formatKey;
        this.pattern = pattern;
    }

    public String getFormatKey() {
        return formatKey;
    }

    public String getPattern() {
        return pattern;
    }

    public static String getPattern(String formatKey) {
        DateTimePatternEnum[] dateTimePatternEnums = DateTimePatternEnum.values();
        for(DateTimePatternEnum dateTimePatternEnum : dateTimePatternEnums) {
            if (Objects.equals(dateTimePatternEnum.getFormatKey(), formatKey)) {
                return dateTimePatternEnum.getPattern();
            }
        }

        return "";
    }
}

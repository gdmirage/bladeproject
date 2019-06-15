package com.blade.practice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtil {

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
    }

    /**
     * covert {@link LocalDateTime} to {@link String}
     *
     * @param localDateTime localDateTime
     * @return return date str by default pattern {@link DateUtil.YYYY_MM_DD_HH_MM_SS}
     */
    public static String dateToStr(LocalDateTime localDateTime) {
        return dateToStr(localDateTime, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * covert {@link LocalDateTime} to {@link String}
     * @param localDateTime localDateTime
     * @param pattern covert pattern
     * @return return date str by covert pattern
     */
    public static String dateToStr(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter();
        return localDateTime.format(formatter);
    }


}

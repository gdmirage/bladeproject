package com.blade.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String DATETIME_FORMATTER_1 = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMATTER_1 = "yyyy-MM-dd";
    public static String DATE_FORMATTER_2 = "yyyy/MM/dd";

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
    }

    /**
     * get current date
     *
     * @return {@link LocalDate}
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * get current datetime
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * get 0 o'clock at the first day of the appointed day's month
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getStartDateTimeOfMonth(LocalDate localDate) {
        return getStartDateOfMonth(localDate).atStartOfDay();
    }

    /**
     * get the first day of the appointed day's month
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDate}
     */
    public static LocalDate getStartDateOfMonth(LocalDate localDate) {
        return localDate.minusDays(localDate.getDayOfMonth() - 1);
    }

    /**
     * get 24 o'clock at the last day of the appointed day's month
     * 24 o'clock means the 0 o'clock at the next day
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getEndDateTimeOfMonth(LocalDate localDate) {
        return getEndDateOfMonth(localDate).plusDays(1).atStartOfDay();
    }

    /**
     * get last date of the appointed day's month
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDate}
     */
    public static LocalDate getEndDateOfMonth(LocalDate localDate) {
        return localDate.plusDays(localDate.lengthOfMonth() - localDate.getDayOfMonth());
    }

    /**
     * convert localDateTime to timestamp
     *
     * @param localDateTime {@link LocalDateTime}
     * @return long
     */
    public static long getTime(LocalDateTime localDateTime) {
        return getTime(localDateTime, ZoneId.systemDefault());
    }

    /**
     * convert localDateTime to timestamp
     *
     * @param localDateTime {@link LocalDateTime}
     * @param zoneId        time zone {@link ZoneId}
     * @return timestamp
     */
    public static long getTime(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * get formatted datetime string
     *
     * @param localDateTime {@link LocalDateTime}
     * @return default pattern "yyyy-MM-dd HH:mm:ss" format datetime string
     */
    public static String getDateTimeString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER_1));
    }

    /**
     * get formatted datetime string
     *
     * @param localDateTime {@link LocalDateTime}
     * @param pattern       format pattern
     * @return appointed pattern format datetime string
     */
    public static String getDateTimeString(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * get formatted date string
     *
     * @param localDate {@link LocalDate}
     * @return default pattern "yyyy-MM-dd" format date string
     */
    public static String getDateString(LocalDate localDate) {
        return getDateString(localDate, DATE_FORMATTER_1);
    }

    /**
     * get formatted date string
     *
     * @param localDate {@link LocalDate}
     * @param pattern   format pattern
     * @return appointed pattern format date string
     */
    public static String getDateString(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * convert date to localDateTime
     *
     * @param date {@link Date}
     * @return {@link LocalDateTime}
     */
    public LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}

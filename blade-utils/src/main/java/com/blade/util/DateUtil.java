package com.blade.util;

import com.blade.util.enums.DateTimePatternEnum;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

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
     * get 0 o'clock of date
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getStartOfDate(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static LocalDateTime getStartOfDate(long date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        return getStartOfDate(localDateTime.toLocalDate());
    }

    /**
     * get 24 o'clock of date, it mean's next day's 0 o'clock
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getEndOfDate(LocalDate localDate) {
        return getStartOfDate(localDate.plusDays(1));
    }

    public static LocalDateTime getEndOfDate(long date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        return getStartOfDate(localDateTime.toLocalDate().plusDays(1));
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
     * the day minus one month
     *
     * @param localDate {@link LocalDate}
     * @return {@link LocalDate}
     */
    public static LocalDate getLastMonthDate(LocalDate localDate) {
        return minusMonths(localDate, 1);
    }

    public static LocalDate getLastMonthDate(long date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        return getLastMonthDate(localDateTime.toLocalDate());
    }

    /**
     * the day minus few month
     *
     * @param localDate       {@link LocalDate}
     * @param monthToSubtract minus num
     * @return {@link LocalDate}
     */
    public static LocalDate minusMonths(LocalDate localDate, long monthToSubtract) {
        return localDate.minusMonths(monthToSubtract);
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
        return getDateTimeString(localDateTime, DateTimePatternEnum.DATE_TIME_FORMAT_1.getPattern());
    }

    /**
     * get formatted datetime string
     *
     * @param localDateTime {@link LocalDateTime}
     * @param pattern       format pattern
     * @return appointed pattern format datetime string
     */
    public static String getDateTimeString(LocalDateTime localDateTime, String pattern) {
        if (null == localDateTime) {
            return "";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * get formatted date string
     *
     * @param localDate {@link LocalDate}
     * @return default pattern "yyyy-MM-dd" format date string
     */
    public static String getDateString(LocalDate localDate) {
        return getDateString(localDate, DateTimePatternEnum.DATE_FORMAT_1.getPattern());
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
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * the day minus few day
     *
     * @param localDate     {@link LocalDate}
     * @param dayToSubtract minus num
     * @return {@link LocalDate}
     */
    public static LocalDate minusDays(LocalDate localDate, long dayToSubtract) {
        return localDate.minusDays(dayToSubtract);
    }

    /**
     * convert string to LocalDate by default pattern "yyyy-MM-dd"
     *
     * @param dateString date string
     * @return {@link LocalDate}
     */
    public static LocalDate convertStringToLocalDate(String dateString) {
        return convertStringToLocalDate(dateString, DateTimePatternEnum.DATE_FORMAT_1.getPattern());
    }

    /**
     * convert long to datetime string
     *
     * @param time time
     * @return datetime string
     */
    public static String convertLongToDatetimeString(Long time) {
        if (null == time || time <= 0) {
            return "";
        }

        return getDateTimeString(convertLong2LocalDateTime(time));
    }

    /**
     * convert string to LocalDate by appointed pattern
     *
     * @param dateString date string
     * @param pattern    date pattern
     * @return {@link LocalDate}
     */
    public static LocalDate convertStringToLocalDate(String dateString, String pattern) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
        return localDate;
    }

    /**
     * convert string to LocalDateTime by default pattern "yyyy-MM-dd"
     *
     * @param datetimeString datetime string
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convertStringToLocalDateTime(String datetimeString) {
        return convertStringToLocalDateTime(datetimeString, DateTimePatternEnum.DATE_TIME_FORMAT_1.getPattern());
    }

    /**
     * convert string to LocalDateTime by appointed pattern
     *
     * @param datetimeString datetime string
     * @param pattern        datetime pattern
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convertStringToLocalDateTime(String datetimeString, String pattern) {
        return LocalDateTime.parse(datetimeString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * convert millisecond (long type) to LocalDateTime
     *
     * @param time millisecond
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convertLong2LocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    /**
     * get number of days from two days
     *
     * @param smallDate the samll date
     * @param bigDate   the big dae
     * @return number
     */
    public static int getNumberOfDaysFromTwoDays(long smallDate, long bigDate) {
        LocalDateTime smallDateTime = convertLong2LocalDateTime(smallDate);
        LocalDateTime bigDateTime = convertLong2LocalDateTime(bigDate);

        return getNumberOfDaysFromTwoDays(smallDateTime, bigDateTime);
    }

    /**
     * get number of days from two days
     *
     * @param smallDate the samll date
     * @param bigDate   the big dae
     * @return number
     */
    public static int getNumberOfDaysFromTwoDays(LocalDateTime smallDate, LocalDateTime bigDate) {
        return bigDate.getDayOfYear() - smallDate.getDayOfYear();
    }

    public static LocalDate plusDays(LocalDate localDate, long days) {
        return localDate.plusDays(days);
    }

    public static void main(String[] args) {
//        System.out.println(getCurrentDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        System.out.println(getNumberOfDaysFromTwoDays(1574390318063L, System.currentTimeMillis()));
    }
}

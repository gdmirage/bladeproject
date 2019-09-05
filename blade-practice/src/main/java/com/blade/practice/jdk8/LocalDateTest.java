package com.blade.practice.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * @author blade
 * 2019/9/5 14:23
 */
public class LocalDateTest {

    public static void main(String[] args) {
        testLocalDate();
    }

    private static void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        System.out.println(localDate.minusDays(localDate.getDayOfMonth() - 1));
//        System.out.println(localDate.plusDays(localDate.lengthOfMonth() - localDate.getDayOfMonth()).plusDays(1).atStartOfDay());

//        localDate = localDate.plusMonths(1);
//        System.out.println(localDate);
//
//        System.out.println(localDate.getDayOfMonth());
//        System.out.println( localDate.getMonth().getValue());
//        localDate = localDate.minusMonths(2).minusDays(localDate.getDayOfMonth() - 1);
//        System.out.println(localDate);
//        System.out.println( localDate.getMonth().maxLength());
//        localDate = localDate.plusDays(localDate.getMonth().maxLength() - 1);
//        System.out.println(localDate);
//        LocalDateTime localDateTime = localDate.atStartOfDay();
//        System.out.println(localDateTime);
//        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println(LocalDateTime.parse("2017-05-04 12:12:12", dtf));
//
//        System.out.println(localDateTime.format(dtf));
    }
}

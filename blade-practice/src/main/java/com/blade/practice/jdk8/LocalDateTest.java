package com.blade.practice.jdk8;

import java.time.LocalDate;

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
        System.out.println(localDate);
    }
}

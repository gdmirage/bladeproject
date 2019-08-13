package com.blade.practice.jdk8.stream;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Blade
 * @date 2019-08-13 17:03:48
 **/
public class StreamTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<Dish> menu = Dish.getMenu();

        List<String> list = menu.stream().filter(dish -> dish.getCalories() > 300) // 筛选高热量的菜单
                .map(Dish::getName) // 获取菜名
                .limit(3)// 获取前3条
                .collect(Collectors.toList());

        System.out.println(list);
    }
}

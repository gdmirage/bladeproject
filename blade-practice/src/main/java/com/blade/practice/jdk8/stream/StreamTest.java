package com.blade.practice.jdk8.stream;

import com.alibaba.fastjson.JSON;
import com.blade.practice.generator.TableField;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Blade
 * @date 2019-08-13 17:03:48
 **/
public class StreamTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        List<Dish> menu = Dish.getMenu();

        List<String> list = menu.stream().filter(dish -> dish.getCalories() > 300) // 筛选高热量的菜单
                .map(Dish::getName) // 获取菜名
                .limit(3)// 获取前3条
                .collect(Collectors.toList());

        System.out.println(list);
    }

    private static void test2() {
        List<TableField> tableFields = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TableField tableField = new TableField();
            if (i % 3 == 0) {
                tableField.setJavaType("");
            }else {
                tableField.setJavaType("type" + i%3);
            }
            tableFields.add(tableField);
        }

        System.out.println(JSON.toJSONString(tableFields));

        List<String> importClassess = tableFields.stream()
                .filter(tableField -> StringUtils.isNotBlank(tableField.getJavaType()))
                .map(TableField::getJavaType).distinct()
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(importClassess));


    }
}

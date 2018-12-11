package com.blade.archetype;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/7 10:34
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.blade.archetype.dao"})
public class TestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);
    }
}

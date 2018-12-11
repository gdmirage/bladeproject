package com.blade.generator;

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
@MapperScan(basePackages = {"com.blade.generator.dao"})
public class TestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);
    }
}

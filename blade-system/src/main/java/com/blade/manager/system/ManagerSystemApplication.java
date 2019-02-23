package com.blade.manager.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/7 10:34
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.blade.manager.system.modules.sys"})
@EnableTransactionManagement
public class ManagerSystemApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ManagerSystemApplication.class, args);
    }
}

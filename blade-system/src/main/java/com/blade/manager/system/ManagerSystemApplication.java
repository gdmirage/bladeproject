package com.blade.manager.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author blade
 * 2019/12/18 16:58
 */
@SpringBootApplication(scanBasePackages = {"com.blade"})
@MapperScan(basePackages = {"com.blade.manager.system"})
@EnableTransactionManagement
public class ManagerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerSystemApplication.class, args);
    }
}

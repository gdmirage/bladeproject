package com.blade.manager.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author blade
 * 2019/12/18 16:58
 */
@SpringBootApplication(scanBasePackages = {"com.blade"})
@MapperScan(basePackages = {"com.blade.manager.system.modules"})
@EnableTransactionManagement
public class ManagerSystemApplication {

}

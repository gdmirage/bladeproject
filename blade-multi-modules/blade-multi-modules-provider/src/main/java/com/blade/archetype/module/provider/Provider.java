package com.blade.archetype.module.provider;

import com.alibaba.dubbo.container.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/6 12:06
 */
public class Provider {

    private static final Logger LOGGER = LoggerFactory.getLogger(Provider.class);

    public static void main(String[] args) throws Exception {

        //启动方式一： tomcat等web容器。但此方式其实是不可取的，它增加了复杂性（端口、管理等方面），也浪费资源（内存）

        // 启动方式二： 借助spring容器启动。此方式也存在一定的缺点：Dubbo本身提供的高级特性没用上；自己编写启动类可能会有缺陷，所以不建议使用，但可用于本地调试。
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/blade-multi-modules-provider.xml");
        context.start();

        while (true) {

        }*/

        // 启动方式三： 使用Dubbo框架提供的Main方法类运行
        //加载配置文件 main方法之前执行
        System.setProperty("dubbo.application.logger", "slf4j");
        LOGGER.info("{} starting dubbo", Provider.class.getSimpleName());
        Main.main(args);
    }
}

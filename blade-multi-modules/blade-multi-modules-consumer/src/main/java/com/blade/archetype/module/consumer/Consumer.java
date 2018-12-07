package com.blade.archetype.module.consumer;

import com.blade.archetype.module.api.ITestApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/6 16:27
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/blade-multi-modules-consumer.xml");

        ITestApi testApi = (ITestApi)context.getBean("testService");
        testApi.sayHello();
    }
}

package com.blade.core.util.spring;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author blade
 * 2019/9/3 10:54
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        ctx = arg0;
    }

    public ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return ctx.getBean(name);
    }
}
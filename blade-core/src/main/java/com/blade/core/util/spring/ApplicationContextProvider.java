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
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        this.ctx = arg0;
    }

    public ApplicationContext getApplicationContext() {
        return this.ctx;
    }

}
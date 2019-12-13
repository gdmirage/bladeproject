package com.blade.archetype;

import com.blade.archetype.interceptors.DefaultHandlerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/7 10:34
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.blade.archetype.dao"})
public class SingleApplication extends WebMvcConfigurerAdapter implements ErrorPageRegistrar{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SingleApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这个是使用spring MVC 的方式，创建拦截器
        registry.addInterceptor(new DefaultHandlerInterceptor());
    }

    /**
     * spring boot 的异常处理
     * @param registry
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
    }
}

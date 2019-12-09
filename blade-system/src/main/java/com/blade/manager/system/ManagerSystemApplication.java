package com.blade.manager.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * TODO: 项目启动类
 *
 * 注意：：： 如果 @SpringBootApplication 注解后面使用了 scanBasePackages 参数，需要把项目的路径也加入扫描中
 *
 * @author chenjiangxin
 * @date 2018/12/7 10:34
 */
@SpringBootApplication(scanBasePackages = {"com.blade"})
@MapperScan(basePackages = {"com.blade.manager.system.modules"})
@EnableTransactionManagement
public class ManagerSystemApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ManagerSystemApplication.class, args);
    }

    /**
     * 增加跨域过滤器
     *
     * @return @link{ CorsFilter}
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //Access-Control-Allow-Credentials：是否允许请求带有验证信息，若要获取客户端域下的 cookie 时，需要将其设置为 true。
        corsConfiguration.setAllowCredentials(true);
        //Access-Control-Allow-Origin：允许访问的客户端域名，例如：http://web.xxx.com，若为 *，则表示从任意域都能访问，即不做任何限制
        corsConfiguration.addAllowedOrigin("*");
        //Access-Control-Allow-Headers：允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type。
        corsConfiguration.addAllowedHeader("*");
        //Access-Control-Allow-Methods：允许访问的方法名，多个方法名用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS。
        corsConfiguration.addAllowedMethod("*");
        //Access-Control-Expose-Headers：允许客户端访问的服务端响应头，多个响应头用逗号分割。
//        corsConfiguration.addExposedHeader("*"); 这个有问题，以后有时间再看看
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}

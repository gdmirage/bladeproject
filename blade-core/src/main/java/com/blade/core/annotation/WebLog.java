package com.blade.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 规范日志打印
 *
 * @author blade
 * 2019/12/5 11:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WebLog {
    /**
     * 描述信息
     *
     * @return description
     */
    String description() default "";
}

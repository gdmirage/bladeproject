package com.blade.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加密注解
 * 使用这个注解，会在入库的时候，把该字段加密
 *
 * @author blade
 * 2019/11/28 15:10
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface Encrypt {
}

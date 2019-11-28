package com.blade.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解密注解
 * 使用这个注解时，在数据库查询到数据，返回的时候，会进行解密
 *
 * @author blade
 * 2019/11/28 15:12
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface Decrypt {
}

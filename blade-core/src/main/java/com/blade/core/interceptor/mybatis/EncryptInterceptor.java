package com.blade.core.interceptor.mybatis;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * 加密拦截器
 * 数据入库之前，根据 注解
 *
 * @author blade
 * 2019/11/28 15:04
 */
@Intercepts(
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
)
public class EncryptInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.debug("in EncryptInterceptor");

        if (invocation.getTarget() instanceof ParameterHandler) {
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];

            Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
            parameterField.setAccessible(true);
            Object parameterObject = parameterField.get(parameterHandler);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

package com.blade.core.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.Properties;

@Intercepts(
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
)
public class ParameterInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.debug("in ParameterInterceptor");
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

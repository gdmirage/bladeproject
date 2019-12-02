package com.blade.core.interceptor.mybatis;

import com.alibaba.fastjson.JSON;
import com.blade.core.annotation.Encrypt;
import com.blade.core.util.AnnotationUtil;
import com.blade.core.util.EncryptUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
            LOGGER.info(JSON.toJSONString(parameterObject));
            if (null != parameterObject) {
                // 如果用 Param 注解，会把参数转成 Map， 这时候，需要对key值进行判断
                if (parameterObject instanceof Map) {
                    HashMap hashMap = (HashMap) parameterObject;
                    Collection<Object> list = hashMap.values();
                    for (Object o : list) {
                        this.encryptParams(o);
                    }
                } else {
                    this.encryptParams(parameterObject);
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 加密sql 参数
     *
     * @param object 待加密的对象
     * @throws IllegalAccessException illegal access exception
     */
    private void encryptParams(Object object) throws IllegalAccessException {
        Class<?> parameterObjectClazz = object.getClass();
        // 只针对当前类， 对于其父类的，一律不校验
        if (AnnotationUtil.fieldHasAnnotation(Encrypt.class, parameterObjectClazz)) {
            LOGGER.debug("class : {} has Encrypt annotation", parameterObjectClazz.getName());
            Field[] fields = parameterObjectClazz.getDeclaredFields();
            for (Field field : fields) {
                Encrypt encrypt = field.getAnnotation(Encrypt.class);
                if (null == encrypt) {
                    continue;
                }

                field.setAccessible(true);

                Class<?> fieldType = field.getType();
                if (fieldType == String.class) {
                    field.set(object, EncryptUtils.Encrypt("privateKey", "aaaaa"));
                }
            }
        }
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

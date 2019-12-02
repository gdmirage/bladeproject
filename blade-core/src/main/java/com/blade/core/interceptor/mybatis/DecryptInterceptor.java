package com.blade.core.interceptor.mybatis;

import com.alibaba.fastjson.JSON;
import com.blade.core.annotation.Decrypt;
import com.blade.core.util.AnnotationUtil;
import com.blade.core.util.EncryptUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author blade
 * 2019/11/28 15:04
 */
@Intercepts(
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
)
public class DecryptInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecryptInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.debug("in DecryptInterceptor");

        Object result = invocation.proceed();

        if (result instanceof List) {
            LOGGER.info(JSON.toJSONString(result));
            ArrayList resultArrayList = (ArrayList) result;
            if (!CollectionUtils.isEmpty(resultArrayList) && AnnotationUtil.fieldHasAnnotation(Decrypt.class,
                    resultArrayList.get(0).getClass())) {
                LOGGER.info("查询的内容需要解密");

                for (Object ra : resultArrayList) {
                    decrypt(ra);
                }

                LOGGER.info(JSON.toJSONString(result));
            }
        } else {
            if (AnnotationUtil.fieldHasAnnotation(Decrypt.class, result.getClass())) {
                // 查询的内容需要解密
                LOGGER.info("查询的内容需要解密");
            }
        }

        return result;
    }

    /**
     * 解密
     *
     * @param o 需要解密的对象
     * @throws IllegalAccessException 异常
     */
    private void decrypt(Object o) throws IllegalAccessException {
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Decrypt decrypt = field.getAnnotation(Decrypt.class);
            if (null == decrypt) {
                continue;
            }

            field.setAccessible(true);

            Class<?> fieldType = field.getType();
            if (fieldType == String.class) {
                field.set(o, EncryptUtils.Decrypt("privateKey", "aaaaa"));
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

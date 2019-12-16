package com.blade.core.advice;

import com.alibaba.fastjson.JSON;
import com.blade.core.exception.ServiceException;
import com.blade.core.exception.SystemException;
import com.blade.core.model.response.ResponseDataEntity;
import com.blade.core.model.response.ResponseEntity;
import com.blade.core.util.Sl4jLoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 基于spring MVC 的统一结果返回
 * 基于spring MVC 的统一结果异常处理
 *
 * @author Blade
 * 2019/12/16 11:39
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseAdvice.class);

    /**
     * 统一的异常处理信息
     *
     * @param e 异常信息
     * @return ResponseEntity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        Sl4jLoggerUtils.debug(LOGGER, "in exception handler");

        Sl4jLoggerUtils.error(LOGGER, e, "catch exception is {}", e.getMessage());
        if (e instanceof ServiceException) {
            return ResponseEntity.exception((ServiceException) e);
        } else if (e instanceof SystemException) {
            return ResponseEntity.exception((SystemException) e);
        } else {
            return ResponseEntity.exception(e.getMessage());
        }

    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("advice supports : {}");
        }
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object responseData, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String url = serverHttpRequest.getURI().getPath();
        Sl4jLoggerUtils.info(LOGGER, "advice beforeBodyWrite : {}", url);

        if (null == responseData) {
            return ResponseEntity.ok();
        }

        if (responseData instanceof ResponseEntity) {
            return responseData;
        }

        // string 特殊处理
        if (responseData instanceof String) {
            return JSON.toJSONString(ResponseDataEntity.ok(responseData));
        }

        return ResponseDataEntity.ok(responseData);
    }
}

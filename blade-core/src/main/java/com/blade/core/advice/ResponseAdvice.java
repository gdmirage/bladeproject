package com.blade.core.advice;

import com.blade.core.exception.ServiceException;
import com.blade.core.exception.SystemException;
import com.blade.core.model.response.ResponseDataEntity;
import com.blade.core.model.response.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 统一的异常处理信息
     *
     * @param e 异常信息
     * @return ResponseEntity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.out.println("----------> in exception handler");
        if (e instanceof ServiceException) {
            return ResponseEntity.exception((ServiceException) e);
        } else if (e instanceof SystemException) {
            return ResponseEntity.exception((SystemException) e);
        } else {
            return ResponseEntity.exception();
        }

    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println("---------> advice supports");
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String url = serverHttpRequest.getURI().getPath();
        System.out.println("---------> advice beforeBodyWrite : " + url);
        if (null == body) {
            return ResponseEntity.ok();
        }

        if (body instanceof ResponseEntity) {
            return body;
        }

        return ResponseDataEntity.ok(body);
    }
}

package com.blade.core.advice;

import com.blade.core.enums.ValidateResultCodeEnum;
import com.blade.core.exception.ServiceException;
import com.blade.core.exception.SystemException;
import com.blade.core.model.response.ResponseDataEntity;
import com.blade.core.model.response.ResponseEntity;
import com.blade.util.FastJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于spring MVC 的统一结果返回
 * 基于spring MVC 的统一结果异常处理
 *
 * @author Blade
 * 2019/12/16 11:39
 */
@RestControllerAdvice(annotations = {RestController.class})
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
        LOGGER.error("catch exception is {}", e.getMessage(), e);

        if (e instanceof MethodArgumentNotValidException) {
            // spring validation 校验
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            Map<String, String> map = new HashMap<>();
            fieldErrorList.forEach(fieldError -> {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            });

            ValidateResultCodeEnum invalidParam = ValidateResultCodeEnum.INVALID_PARAM;
            invalidParam.setSubMsg(map.toString());

            ServiceException exception = new ServiceException(invalidParam);
            return ResponseEntity.exception(exception);
        }

        if (e instanceof ServiceException) {
            return ResponseEntity.exception((ServiceException) e);
        }

        if (e instanceof SystemException) {
            return ResponseEntity.exception((SystemException) e);
        }
        return ResponseEntity.exception(e.getMessage());

    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        LOGGER.debug("advice supports");

        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object responseData, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String url = serverHttpRequest.getURI().getPath();
        LOGGER.info("advice beforeBodyWrite");

        if (null == responseData) {
            return ResponseEntity.ok();
        }

        if (responseData instanceof ResponseEntity) {
            return responseData;
        }

        // string 特殊处理
        if (responseData instanceof String) {
            return FastJsonUtils.toJsonString(ResponseDataEntity.ok(responseData));
        }

        return ResponseDataEntity.ok(responseData);
    }
}

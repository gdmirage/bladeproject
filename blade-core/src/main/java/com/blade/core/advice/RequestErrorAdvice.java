package com.blade.core.advice;

import com.blade.core.exception.ServiceException;
import com.blade.core.exception.SystemException;
import com.blade.core.model.response.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * spring MVC 的 异常拦截
 *
 * @author blade
 * 2019/12/12 17:42
 */
@RestControllerAdvice
public class RequestErrorAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity ExceptionHandler(Exception e) {

        if (e instanceof ServiceException) {
            return ResponseEntity.exception((ServiceException) e);
        } else if (e instanceof SystemException) {
            return ResponseEntity.exception((SystemException) e);
        } else {
            return ResponseEntity.exception();
        }

    }
}

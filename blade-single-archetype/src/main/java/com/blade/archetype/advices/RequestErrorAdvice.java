package com.blade.archetype.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDefinitionException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 * spring MVC 的异常拦截
 * @author Blade
 * @date 2019/4/29 10:01
 */
@RestControllerAdvice
public class RequestErrorAdvice {

    @ExceptionHandler(value = Exception.class)
    public String pageNotFound(Exception e) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("statusCode", 00000);
        errors.put("exception", e.getMessage());

        return e.getMessage();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object validationError(Exception e) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("code", "000000");
        errors.put("msg", e.getMessage());

        return errors;
    }
}

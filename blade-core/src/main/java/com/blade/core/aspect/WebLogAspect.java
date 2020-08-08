package com.blade.core.aspect;

import com.alibaba.fastjson.JSON;
import com.blade.core.annotation.WebLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * web 日志打印
 *
 * @author blade
 * 2019/12/4 17:16
 */
@Component
@Aspect
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@annotation(com.blade.core.annotation.WebLog)")
    public void pointCut() {
    }

    /**
     * 异常不处理，交由给统一拦截器处理
     * @param proceedingJoinPoint {@link ProceedingJoinPoint}
     * @return obj
     * @throws Throwable Throwable
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object o = proceedingJoinPoint.proceed();

        logger.info("Response Args  : {}", JSON.toJSONString(o));

        long end = System.currentTimeMillis();

        logger.info("Time-Consuming : {} ms", (end - start));
        return o;
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 打印日志请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取@WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        logger.info("=============================== Start ================================");
        logger.info("URL             : {}", request.getRequestURL().toString());
        logger.info("Description     : {}", methodDescription);
        logger.info("Http Method     : {}", request.getMethod());
        logger.info("Class Method    : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info("IP              : {}", request.getRemoteAddr());
        logger.info("Request Args    : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    @After("pointCut()")
    public void doAfter() throws Exception {
        logger.info("=============================== End ===================================");
    }

    private String getAspectLogDescription(JoinPoint joinPoint) throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (Objects.equals(method.getName(), methodName)) {
                Class[] classes = method.getParameterTypes();
                if (classes.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }

        return description.toString();
    }
}


package com.blade.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author blade
 * 2019/12/4 17:16
 */
@Component("logAspect")
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.blade.core.annotation.CalculateExecuteTime)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("around method : {}", proceedingJoinPoint);
        long start = System.currentTimeMillis();
        try {
            Object o = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            logger.info("interface: {},response time is {} ms",proceedingJoinPoint, (end - start));
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

//    @Around("execution(* com.blade..controller..*(..))")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        logger.info("around method");
//        long start = System.currentTimeMillis();
//        try {
//            Object o = joinPoint.proceed();
//            long end = System.currentTimeMillis();
//
//            logger.info("接口响应为: {} ms", (end - start));
//            return o;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            return null;
//        }
//    }
}


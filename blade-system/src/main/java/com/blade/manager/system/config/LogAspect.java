//package com.blade.manager.system.config;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author blade
// * 2019/12/4 17:16
// */
//@Component
//@Aspect
//public class LogAspect {
//
//    private Logger logger = LoggerFactory.getLogger(LogAspect.class);
//
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
//}
//

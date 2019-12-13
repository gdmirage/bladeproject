package com.blade.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 计算方法执行时间的切面
 *
 * @author blade
 * 2019/12/4 17:16
 */
@Component
@Aspect
public class CalculateMethodExecuteTimeAspect {

    private Logger logger = LoggerFactory.getLogger(CalculateMethodExecuteTimeAspect.class);

    @Pointcut("@annotation(com.blade.core.annotation.CalculateMethodExecuteTime)")
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
        long end = System.currentTimeMillis();

        logger.info("interface: {},response time is {} ms", proceedingJoinPoint, (end - start));
        return o;
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


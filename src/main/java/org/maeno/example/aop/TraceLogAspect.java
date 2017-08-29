package org.maeno.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class TraceLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(org.maeno.example.controller..*)")
    public void packagePointcut() {
        // do nothing
    }

    @Around("packagePointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter: {}.{}() with argument[s] = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
        final Object proceed = joinPoint.proceed();
        if (logger.isDebugEnabled()) {
            logger.debug("Exit: {}.{}() with result = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    proceed);
        }
        return proceed;
    }
}

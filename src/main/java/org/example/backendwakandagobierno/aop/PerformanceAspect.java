package org.example.backendwakandagobierno.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* org.example.backendwakandagobierno.service..*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("RENDIMIENTO: Método " + methodName + " ejecutado en " + (endTime - startTime) + " ms");

        return result;
    }
}

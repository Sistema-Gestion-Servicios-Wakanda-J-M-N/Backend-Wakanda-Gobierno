package org.example.backendwakandagobierno.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class UnifiedMetricsAspect {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedMetricsAspect.class);
    private final ConcurrentHashMap<String, AtomicInteger> methodCallCounts = new ConcurrentHashMap<>();

    // Pointcut para interceptar métodos en los servicios
    @Pointcut("execution(* org.example.backendwakandagobierno.service..*(..))")
    public void serviceLayerMethods() {}


    @Around("serviceLayerMethods()")
    public Object monitorMetrics(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        // Incrementar conteo de llamadas
        methodCallCounts.putIfAbsent(methodName, new AtomicInteger(0));
        int count = methodCallCounts.get(methodName).incrementAndGet();

        // Medir tiempo de ejecución
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        // Registrar métricas
        logger.info("MÉTRICAS: Método {} llamado {} veces. Tiempo de ejecución: {} ms", methodName, count, elapsedTime);

        return result;
    }
}

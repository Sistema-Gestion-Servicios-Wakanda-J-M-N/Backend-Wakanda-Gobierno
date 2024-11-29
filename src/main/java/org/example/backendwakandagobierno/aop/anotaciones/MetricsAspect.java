package org.example.backendwakandagobierno.aop.anotaciones;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class MetricsAspect {

    private final ConcurrentHashMap<String, AtomicInteger> methodCallCounts = new ConcurrentHashMap<>();

    @Around("execution(* org.example.backendwakandagobierno.service..*(..)) && @annotation(Metrics)")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        // Incrementar conteo de llamadas
        methodCallCounts.putIfAbsent(methodName, new AtomicInteger(0));
        methodCallCounts.get(methodName).incrementAndGet();

        // Medir tiempo de ejecución
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        System.out.println("MÉTRICAS: Método " + methodName + " llamado " + methodCallCounts.get(methodName).get() + " veces. Tiempo de ejecución: " + (endTime - startTime) + "ms");

        return result;
    }
}

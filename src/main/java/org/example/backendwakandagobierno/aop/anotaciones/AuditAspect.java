package org.example.backendwakandagobierno.aop.anotaciones;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @After("execution(* org.example.backendwakandagobierno.service..*(..)) && @annotation(Auditable)")
    public void audit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        LocalDateTime timestamp = LocalDateTime.now();
        Object[] args = joinPoint.getArgs();

        logger.info("AUDITORÍA: Método {} ejecutado en {} con argumentos: {}", methodName, timestamp, args);
        // Aquí puedes extender el registro a una base de datos o archivo si es necesario.
    }
}


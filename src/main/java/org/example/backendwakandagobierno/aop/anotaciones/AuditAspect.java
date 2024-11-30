package org.example.backendwakandagobierno.aop.anotaciones;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    // Pointcut para interceptar métodos anotados con @Auditable
    @Pointcut("execution(* org.example.backendwakandagobierno.service..*(..)) && @annotation(Auditable)")
    public void auditableMethods() {}


    @After("auditableMethods()")
    public void audit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        LocalDateTime timestamp = LocalDateTime.now();
        Object[] args = joinPoint.getArgs();

        logger.info("AUDITORÍA: Método {} ejecutado en {} con argumentos: {}", methodName, timestamp, args);
    }
}

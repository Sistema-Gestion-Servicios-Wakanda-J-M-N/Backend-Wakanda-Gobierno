package org.example.backendwakandagobierno.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingAspect.class);

    // Pointcut para interceptar métodos en los servicios
    @Pointcut("execution(* org.example.backendwakandagobierno.service..*(..))")
    public void serviceLayerMethods() {}


    @AfterThrowing(pointcut = "serviceLayerMethods()", throwing = "exception")
    public void handleException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        // Registrar el error con detalles adicionales
        logger.error("Error en el método: {} con argumentos: {}", methodName, methodArgs, exception);
    }
}
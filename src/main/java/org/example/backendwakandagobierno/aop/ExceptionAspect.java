package org.example.backendwakandagobierno.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class ExceptionAspect {

    @AfterThrowing(pointcut = "execution(* org.example.backendwakandagobierno.service..*(..))", throwing = "ex")
    public void handleException(Exception ex) {
        System.err.println("EXCEPCIÓN CAPTURADA: " + ex.getMessage());
        // Aquí puedes agregar lógica para registrar en logs o enviar alertas.
    }
}


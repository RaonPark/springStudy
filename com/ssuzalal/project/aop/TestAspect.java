package com.ssuzalal.project.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TestAspect {
    @AfterThrowing(value = "@annotation(org.springframework.stereotype.Controller)", throwing = "exception")
    public void controllerErrorHandler(JoinPoint jp, Throwable exception) {
        try {
            var sig = jp.getSignature();
            String methodName = sig.getName();

            log.info("Error in method : " + methodName + " error message : " + exception.getMessage());

        } catch(Throwable e) {
            log.info("Error in aspect " + e.getMessage());
        }
    }

    @AfterThrowing(value = "@annotation(org.springframework.stereotype.Service)", throwing = "exception")
    public void serviceErrorHandler(JoinPoint jp, Throwable exception) {
        try {
            var sig = jp.getSignature();
            String methodName = sig.getName();

            log.info("Error in method : " + methodName + " error message : " + exception.getMessage());

        } catch(Throwable e) {
            log.info("Error in aspect " + e.getMessage());
        }
    }
}

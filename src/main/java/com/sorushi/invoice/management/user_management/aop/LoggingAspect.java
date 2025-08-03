package com.sorushi.invoice.management.user_management.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  @Pointcut("execution(* com.sorushi.invoice.management..service..*(..))")
  public void serviceMethods() {}

  @Before("serviceMethods()")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Entering method: {} with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
  }

  @After("serviceMethods()")
  public void logAfter(JoinPoint joinPoint) {
    log.info("Exiting method: {}", joinPoint.getSignature());
  }

  @AfterReturning(value = "serviceMethods()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    log.info("Method {} completed successfully with result: {}", joinPoint.getSignature(), result);
  }

  @AfterThrowing(value = "serviceMethods()", throwing = "ex")
  public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
    log.error("Method {} threw exception: {}", joinPoint.getSignature(), ex.getMessage(), ex);
  }
}

package com.source.plusutil.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerParameterCheckAop {

    @Pointcut("execution(* com.source.plusutil..*Controller.*(..))")
    private void cut() { }

    //컨트롤러로 들어오는 최초 파라미터 점검
    @Before("cut()")
    public void parameterCheck(JoinPoint joinPoint) {
        //MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //Method method = methodSignature.getMethod();
        log.info("\n[AOP] " + joinPoint.toString());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("\n[AOP] type: " + arg.getClass().getSimpleName() + "value: " + arg);
        }
    }
}

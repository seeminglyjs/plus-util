package com.source.plusutil.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class MethodSpeedCheckAop {

    //서비스 만 체크한다.
    @Around("execution(* com.source.plusutil..*Service.*(..)) " +
            "&& !within(com.source.plusutil.config..*)"+
            "&& !within(com.source.plusutil.filter..*)")
    public Object checkSpeedStart(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        log.debug("\n[AOP] startTime ms -> " + startTime + "\n startTime s -> " + TimeUnit.MILLISECONDS.toSeconds(startTime));
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long finishTime = System.currentTimeMillis();
            log.debug("\n[AOP] finishTime ms -> " + finishTime + "\n finishTime s -> " + TimeUnit.MILLISECONDS.toSeconds(finishTime));
            long timeMs = finishTime - startTime;
            long second = TimeUnit.MILLISECONDS.toSeconds(timeMs);
            log.info("\n[AOP] ======= END: " + joinPoint +
                    " \n[AOP] ======= " + timeMs + "ms"+
                    " \n[AOP] ======= " + second + "s");
            if(second >= 3 && second < 30){
                log.warn("[AOP] This Method "+ joinPoint.getSignature()+ "takes too long to run."); //3초 이상 걸릴 경우 경고한다.
            }else if (second >= 30){
                log.error("[AOP] This Method "+ joinPoint.getSignature()+ "takes too long to run. [OVER 30 SECOND!!!!!!!!!!]"); //30초 이상 걸릴 경우 에러로그
            }
        }
    }
}

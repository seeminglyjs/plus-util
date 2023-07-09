package com.source.plusutil.aop;

import com.source.plusutil.admin.dto.AdminRoleResponseDto;
import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AdminRoleCheckAop {

//    @Pointcut("execution(* (com.source.plusutil.utilInfo..*Service || com.source.plusutil.notice..*Service).*(..)) " +
//            "&& !execution(* com.source.plusutil.utilInfo..*Service.clickUtilInfo(..))")
    @Pointcut("execution(* (com.source.plusutil.utilInfo..*Controller).*(..)) " +
            "&& !execution(* com.source.plusutil.utilInfo..*Controller.clickUtilInfo(..))" +
            "&& !execution(* com.source.plusutil.utilInfo..*Controller.getUtilTopList(..))"+
            "&& !execution(* com.source.plusutil.utilInfo..*Controller.getUtilInfoDetail(..))"+
            "&& !execution(* com.source.plusutil.utilInfo..*Controller.checkLikeUtilInfo(..))"+
            "&& !execution(* com.source.plusutil.utilInfo..*Controller.likeUtilInfo(..))"
    )
    private void cut() { }

    @Around("cut()")
    public Object parameterCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().getAuthentication(), UserRolePlusEnum.ROLE_ADMIN.toString())) {
            log.info("\n[AOP] ROLE_ADMIN OK ========");
            return joinPoint.proceed(); // 원래 메서드 실행
        } else {
            log.info("\n[AOP] ROLE_ADMIN NO ========");
            // 원하는 리턴값을 제공하거나 예외를 던질 수 있습니다.
            return AdminRoleResponseDto.builder()
                    .auth(false)
                    .build(); // 예시로 null을 리턴하도록 설정
        }
    }
}

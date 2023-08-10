package com.source.plusutil.user

import com.source.plusutil.user.dto.LoginCheckDto
import com.source.plusutil.utils.auth.AuthObjectUtil
import com.source.plusutil.utils.auth.dto.AuthObject
import lombok.RequiredArgsConstructor
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

import io.github.oshai.KotlinLogging

private val logger = KotlinLogging.logger {}
@RestController
@RequestMapping("/plus")
@RequiredArgsConstructor
class LoginController {
    @Deprecated("")
    @GetMapping("/login")
    @ResponseBody
    fun loginView(authentication: Authentication): LoginCheckDto {
        val loginCheckDto = LoginCheckDto()
        try {
            val authInfoOp = Optional.ofNullable(authentication.details as WebAuthenticationDetails)
            if (authInfoOp.isPresent) {
                val authInfo = authInfoOp.get()
                val ipOp = Optional.ofNullable(authInfo.remoteAddress)
                val sessionIdOp = Optional.ofNullable(authInfo.sessionId)
                if (ipOp.isPresent && sessionIdOp.isPresent) {
                    logger.info("IP : ${authInfo.remoteAddress}")
                    logger.info(("Session ID : ${authInfo.remoteAddress}"))

                    // 권한 리스트
                    val authList = authentication.authorities.toList()
                    logger.info("USER_ROLE_INFO : ")
                    for (grantedAuthority in authList) {
                        logger.info(grantedAuthority.authority + " ")
                    }
                    //					return HomeReturnUrl.HOME_MAIN.getUrl();
                    loginCheckDto.loginCheck = true
                    logger.info(loginCheckDto.toString())
                    return loginCheckDto
                } else {
                    logger.info("[Login info empty] =====")
                }
            } else {
                logger.info("[Login info(authInfoOp) NullPointer] =====")
            }
        } catch (e: NullPointerException) {
            logger.info("[Login info NullPointer] =====")
        }
        logger.info(loginCheckDto.toString())
        return loginCheckDto
    }

    @GetMapping("/auth")
    @ResponseBody
    fun loginView(): AuthObject {
        return AuthObjectUtil.makeAuthObject(SecurityContextHolder.getContext().authentication)
    }
}
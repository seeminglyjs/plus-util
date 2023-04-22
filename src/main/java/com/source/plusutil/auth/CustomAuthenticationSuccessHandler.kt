package com.source.plusutil.auth

import io.github.oshai.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val logger = KotlinLogging.logger {}
class CustomAuthenticationSuccessHandler : SavedRequestAwareAuthenticationSuccessHandler(){
    @Throws(ServletException::class, IOException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?,
                                authentication: Authentication?) {
        logger.debug("CustomAuthenticationSuccessHandler.onAuthenticationSuccess ::::")
        /*
         * 쿠키에 인증 토큰을 넣어준다.
         */super.onAuthenticationSuccess(request, response, authentication)
    }
}
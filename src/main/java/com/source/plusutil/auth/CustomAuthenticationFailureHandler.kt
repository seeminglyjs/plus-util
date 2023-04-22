package com.source.plusutil.auth

import io.github.oshai.KotlinLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val logger = KotlinLogging.logger {}
class CustomAuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?,
                                         exception: AuthenticationException?) {
        logger.debug("CustomAuthenticationFailureHandler.onAuthenticationFailure ::::")
        super.onAuthenticationFailure(request, response, exception)
    }
}
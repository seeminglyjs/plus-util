package com.source.plusutil.filter.login;

import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns= {"/plus/*"})
@Order(98)
@Slf4j
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("===== AuthenticationFilter init =====");
    }

    @Override
    public void doFilter(ServletRequest serRequest, ServletResponse serResponse, FilterChain chain) throws IOException, ServletException {
        Object authentication = new Object();
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (NullPointerException e) {
            log.info("authentication is empty =====");
        }

        HttpServletRequest request = (HttpServletRequest) serRequest;
        HttpServletResponse response = (HttpServletResponse) serResponse;
        if (authentication instanceof Authentication) {//현재 객체가 UserDetails 정보를 가지고 있는지 확인
            if(AuthObjectUtil.authenticationConfirm((Authentication) authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) {
                request.setAttribute("AuthenticationCheck", "ok");
            }else if(AuthObjectUtil.authenticationConfirm((Authentication) authentication, UserRolePlusEnum.ROLE_USER.toString())){
                request.setAttribute("AuthenticationCheck", "ok");
            }else {
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
                request.setAttribute("AuthenticationCheck", null);
            }
        } else {
            request.setAttribute("AuthenticationCheck", null);
            log.debug("Authentication User Info -> ===== Empty");
        }
        chain.doFilter(request, response);
    }
}

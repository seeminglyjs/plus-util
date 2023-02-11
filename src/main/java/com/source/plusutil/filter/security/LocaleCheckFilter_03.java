package com.source.plusutil.filter.security;

import com.source.plusutil.utils.http.HttpParamCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(urlPatterns= {"/plus/*"})
@Order(3) //번째 동작필터
@Slf4j
public class LocaleCheckFilter_03 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("===== LocaleCheckFilter init =====");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Map<String,String> localMap = HttpParamCheckUtil.localeCheck(request);
        log.info(localMap.toString());
        if(!localMap.get("code").equals("**") && !localMap.get("code").equals("KR") ){
            response.sendRedirect("/error/main");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

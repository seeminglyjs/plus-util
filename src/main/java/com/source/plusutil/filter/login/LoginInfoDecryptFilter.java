package com.source.plusutil.filter.login;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.utils.encrypt.AesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns= {"/plus/*"})
@Order(97)
@Slf4j
@RequiredArgsConstructor
public class LoginInfoDecryptFilter implements Filter {

    private final PropertiesConfig config;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("===== LoginInfoDecryptFilter init =====");
    }

    @Override
    public void doFilter(ServletRequest serRequest, ServletResponse serResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) serRequest;
        HttpServletResponse response = (HttpServletResponse) serResponse;

        if(request.getParameter("userEmail") != null && request.getParameter("userPassword") != null ){
            // Decrypt userEmail and userPassword here
            String decryptedUserEmail = AesUtil.aes256Decrypt(config.getAes256key(), config.getAes256iv(), request.getParameter("userEmail")).get("decryptContent");
            String decryptedUserPassword = AesUtil.aes256Decrypt(config.getAes256key(), config.getAes256iv(), request.getParameter("userPassword")).get("decryptContent");

            log.info("decryptedUserEmail -> " + decryptedUserEmail);
            log.info("decryptedUserPassword -> " + decryptedUserPassword);

            // Replace the original request parameters with decrypted values
            request.setAttribute("userEmail", decryptedUserEmail);
            request.setAttribute("userPassword", decryptedUserPassword);
        }

        chain.doFilter(request, response);
    }
}
package com.source.plusutil.filter.cookies;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns= {"/plus/*"})
@Order(99) //가장 마지막 동작 필터
@Slf4j
public class CookieInfoSetAttributeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) {
		log.info("===== CookieCheck init =====");
	}

	public void doFilter(ServletRequest serRequest, ServletResponse serResponse, FilterChain chain)
			throws IOException, ServletException {

		log.debug("===== CookieCheck start =====");
		HttpServletRequest request = (HttpServletRequest)serRequest;
		HttpServletResponse response = (HttpServletResponse)serResponse;


		Optional<Cookie []> cookiesOpt = Optional.ofNullable(request.getCookies()); //최초 톰켓 실행시 cookie 없을때 발생하는 nullpointerexception 방지  
		boolean cookieFlag = false;

		if(cookiesOpt.isPresent()) { //쿠키가 하나라도 있을 경우
			Cookie [] cookies = cookiesOpt.get();
			for(Cookie cookie : cookies) { //쿠키값을 확인하며 로그인 정보 체크
				if(cookie.getName().equals("loginOk")) {
					cookieFlag = true;
					log.debug("login ok cookie info -> " + cookie.getValue());
					break;
				}
			}
		}

		if(cookieFlag) {//로그인 여부 확인
			log.debug("cookieFlag true =====");
			request.setAttribute("loginCookieInfo", "ok");
		}else {
			log.debug("cookieFlag false =====");
			request.setAttribute("loginCookieInfo", null);
		}	

		chain.doFilter(request, response);
	}
}

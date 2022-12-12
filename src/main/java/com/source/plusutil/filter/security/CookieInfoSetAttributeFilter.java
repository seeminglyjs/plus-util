package com.source.plusutil.filter.security;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns="/*")
@Slf4j
public class CookieInfoSetAttributeFilter implements Filter{
	
	private final String JSESSIONID = "JSESSIONID";
	private final String REMEMBERME = "remember-me";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("===== CookieCheck init =====");
	}
	
	public void doFilter(ServletRequest serRequest, ServletResponse serResponse, FilterChain chain)
			throws IOException, ServletException {
		
		log.info("===== CookieCheck start =====");
		HttpServletRequest request = (HttpServletRequest)serRequest;
		HttpServletResponse response = (HttpServletResponse)serResponse;
		
		Cookie [] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			
			if(cookies[i].getName().equals(REMEMBERME) || cookies[i].getName().equals(JSESSIONID)){// JSESSIONID / remember-me 정보 attribute로 저장
				request.setAttribute(cookies[i].getName(), cookies[i].getValue());
				log.info("cookies[i].getName() -> " + cookies[i].getName());
				log.info("cookies[i].getValue() -> " + cookies[i].getValue());
				
			}
		}
		chain.doFilter(request, response);
	}
}

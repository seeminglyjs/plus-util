package com.source.plusutil.handler.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	//로그인 실패에 대한 핸들링
	
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
		 log.info("[UserLogin Failure] =====");
		 setDefaultFailureUrl("/login"); //로그인 실패시 홈페이지로 보낸다.	
			super.onAuthenticationFailure(request, response, exception);
		}

}

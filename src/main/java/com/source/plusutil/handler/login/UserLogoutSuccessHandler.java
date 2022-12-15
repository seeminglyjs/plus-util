package com.source.plusutil.handler.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{//사용자가 로그아웃했을때 핸들링 해줄 클래스
	
	@Override //로그아웃에 성공했을 경우
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			log.info("[UserLogoutSuccessHandler Success] =====");
			
			Cookie loginCheckCookie = new Cookie("loginOk", null); //기존쿠키값 null 할당
			loginCheckCookie.setMaxAge(0); //수명죽이기
			loginCheckCookie.setPath("/"); //모든 url 적용
			response.addCookie(loginCheckCookie);
			
			super.onLogoutSuccess(request, response, authentication);
		}
}

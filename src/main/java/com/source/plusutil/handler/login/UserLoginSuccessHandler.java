package com.source.plusutil.handler.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.source.plusutil.enums.returnUrl.HomeReturnUrl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	//로그인 성공에 대한 핸들링

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		log.info("[UserLogin Success] =====");

		// IP, 세션 ID
		WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
		log.info("IP : " + web.getRemoteAddress());
		log.info("Session ID : " + web.getSessionId());

		// 인증 ID
		log.info("name : " + authentication.getName());

		// 권한 리스트
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
		log.info("권한 : ");
		for(int i = 0; i< authList.size(); i++) {
			log.info(authList.get(i).getAuthority() + " ");
		}
		Cookie loginCheckCookie = new Cookie("loginOk", "success");
		loginCheckCookie.setMaxAge(60*60*24); //수명지정하기 하루로 설정
		loginCheckCookie.setPath("/"); //모든 url 적용
		response.addCookie(loginCheckCookie);
		
		setDefaultTargetUrl(HomeReturnUrl.NEXT_HOME_MAIN.getUrl()); //로그인 성공시 홈페이지로 보낸다.
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

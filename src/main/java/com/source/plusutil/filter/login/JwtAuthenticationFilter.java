package com.source.plusutil.filter.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils; deprecated 되었음...

import lombok.extern.slf4j.Slf4j;

@Slf4j //[******************************미사용************************************]
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	//form action을 post를 받을지 여부 체크하는 변수
	private boolean postOnly = true;
	
//	Form Login시 걸리는 Filter
//	UsernamePasswordAuthenticationFilter를 상속하며, 
//	사용자가 Form으로 입력한 로그인 정보를 인터셉트해서 AuthenticationManager에게 Authentication 객체를 넘겨줍니다.

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
    
    /*
     * 해당 필터에서 인증 프로세스 이전에 요청에서 사용자 정보를 가져와서
     * Authentication 객체를 인증 프로세스 객체에게 전달하는 역할
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.debug("[JwtAuthentication.attemptAuthentication] =====");
        
        /*
         * POST로 넘어왔는지 체크
         */
        if(postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        
        if(ObjectUtils.isEmpty(username)) {
            username = "";
        }
        if(ObjectUtils.isEmpty(password)) {
            password = "";
        }
        
        username = username.trim();//빈문자열제거
        
        //유저정보 기준 인증객체(토큰) 생성
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        
        setDetails(request, authRequest);
        
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    
    
    /**
     * 사용자 이름은 -> 사용자 아이디 메소드 재정의
     * 
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
    	String userId = request.getParameter("userId");
    	return userId;
    }    
}

package com.source.plusutil.controller.loginController;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/plus")
public class LoginController {

    @GetMapping("/login")
    public String loginView(HttpServletRequest request, Authentication authentication) {
    	
    	try {
    		Optional<WebAuthenticationDetails> authInfoOp = Optional.ofNullable((WebAuthenticationDetails) authentication.getDetails());
        	if(authInfoOp.isPresent()) {
        		WebAuthenticationDetails authInfo = authInfoOp.get();
        		if(authInfo != null) {
        			Optional<String> ipOp = Optional.ofNullable(authInfo.getRemoteAddress());
        			Optional<String> sessionIdOp = Optional.ofNullable(authInfo.getSessionId());
        			if(ipOp.isPresent() && sessionIdOp.isPresent()) {
        				log.info("IP : " + authInfo.getRemoteAddress());
        				log.info("Session ID : " + authInfo.getSessionId());

        				// 권한 리스트
        				@SuppressWarnings("unchecked")
        				List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
        				log.info("USER_ROLE_INFO : ");
        				for(int i = 0; i< authList.size(); i++) {
        					log.info(authList.get(i).getAuthority() + " ");
        				}     				
        				return "/home/homeMain";
        			}else {
        				log.info("[Login info empty] =====");
        			}
        		}	
        	}else {
        		log.info("[Login info(authInfoOp) NullPointer] =====");
        	}
    	}catch (NullPointerException e) {
    		log.info("[Login info NullPointer] =====");
		}
    		
        return "/login/loginMain";
    }
}

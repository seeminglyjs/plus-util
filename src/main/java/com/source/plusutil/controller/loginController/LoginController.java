package com.source.plusutil.controller.loginController;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.returnUrl.HomeReturnUrl;
import com.source.plusutil.enums.returnUrl.LoginReturnUrl;
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
				Optional<String> ipOp = Optional.ofNullable(authInfo.getRemoteAddress());
				Optional<String> sessionIdOp = Optional.ofNullable(authInfo.getSessionId());
				if(ipOp.isPresent() && sessionIdOp.isPresent()) {
					log.info("IP : " + authInfo.getRemoteAddress());
					log.info("Session ID : " + authInfo.getSessionId());

					// 권한 리스트
					@SuppressWarnings("unchecked")
					List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
					log.info("USER_ROLE_INFO : ");
					for (GrantedAuthority grantedAuthority : authList) {
						log.info(grantedAuthority.getAuthority() + " ");
					}
					return HomeReturnUrl.HOME_MAIN.getUrl();
				}else {
					log.info("[Login info empty] =====");
				}
			}else {
        		log.info("[Login info(authInfoOp) NullPointer] =====");
        	}
    	}catch (NullPointerException e) {
    		log.info("[Login info NullPointer] =====");
		}
    		
        return LoginReturnUrl.LOGIN_MAIN.getUrl();
    }
}

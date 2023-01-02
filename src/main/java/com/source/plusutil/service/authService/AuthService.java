package com.source.plusutil.service.authService;

import org.springframework.security.core.Authentication;

public interface AuthService {
	
	public boolean authenticationConfirm(Authentication authentication, String role);
}

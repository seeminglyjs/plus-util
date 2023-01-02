package com.source.plusutil.service.authService;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthService{ //서비스 요청 유저 권한 확인 서비스

	@SuppressWarnings("unchecked")
	@Override
	public boolean authenticationConfirm(Authentication authentication, String role) {
		Optional<List<GrantedAuthority>> authListOp = null;
		try {
			authListOp = Optional.ofNullable((List<GrantedAuthority>) authentication.getAuthorities());
		}catch (NullPointerException e) {
			log.info("authentication.getAuthorities() -> Null");
			return false;
		}

		if(!authListOp.isPresent()) {
			return false;
		}else {
			List<GrantedAuthority> authList = authListOp.get();
			log.info("권한 : ");
			boolean authCheck = false;
			for(int i = 0; i< authList.size(); i++) {
				log.info(authList.get(i).getAuthority() + " ");
				if(authList.get(i).getAuthority().equals(role)) {
					authCheck = true;
				}
			}

			if(!authCheck) { //권한 없으면 게시글 못씀
				log.info("writeNotice Deny -> No ROLE_ADMIN" );
				return false;
			}
		}
		
		return true;
	}
}
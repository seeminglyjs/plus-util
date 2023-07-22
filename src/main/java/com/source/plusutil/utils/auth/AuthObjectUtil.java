package com.source.plusutil.utils.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AuthObjectUtil {

    public static boolean authenticationConfirm(Authentication authentication, String role) {
        Collection<? extends GrantedAuthority> authList;
        try {
            authList = authentication.getAuthorities();
        }catch (NullPointerException e) {
            log.info("authentication.getAuthorities() -> Null");
            return false;
        }

        if(authList == null || authList.isEmpty()) {
            return false;
        }else {
            log.info("권한 : ");
            boolean authCheck = false;
            for (GrantedAuthority grantedAuthority : authList) {
                log.info(grantedAuthority.getAuthority() + " ");
                if (grantedAuthority.getAuthority().equals(role)) {
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

package com.source.plusutil.utils.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.source.plusutil.user.dto.UserInfoDto;
import com.source.plusutil.utils.auth.dto.AuthObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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


    public static AuthObject makeAuthObject(Authentication authentication){
        Collection<? extends GrantedAuthority> authList;
        try {
            authList = authentication.getAuthorities();
        }catch (NullPointerException e) {
            log.info("authentication.getAuthorities() -> Null");
            return AuthObject.builder().authenticated(false).userNo(-1).build();
        }
        if(authList == null || authList.isEmpty()) {
            return AuthObject.builder().authenticated(false).userNo(-1).build();
        }else {
            log.info("권한 : ");
            for (GrantedAuthority grantedAuthority : authList) {
                log.info(grantedAuthority.getAuthority() + " ");
                if(grantedAuthority.getAuthority() != null){

                    try{
                        UserInfoDto userInfoDto = (UserInfoDto) authentication.getPrincipal();
                        return AuthObject.builder()
                                .authenticated(authentication.isAuthenticated())
                                .userNo(userInfoDto.getUserNo())
                                .userEmail(userInfoDto.getUserEmail())
                                .userRole(userInfoDto.getUserRole())
                                .build();
                    }catch (Exception e){
                        log.info(e.getMessage() + "->  UserInfoDto userInfoDto = (UserInfoDto) authentication.getPrincipal()");
                        return AuthObject.builder().authenticated(false).userNo(-1).build();
                    }
                }
            }
        }
        return AuthObject.builder().authenticated(false).userNo(-1).build();
    }
}

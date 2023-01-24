package com.source.plusutil.utils.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;

@Slf4j
public class AuthObjectUtil {

    public boolean authenticationConfirm(Authentication authentication, String role) {
        Optional<List<GrantedAuthority>> authListOp;
        try {
            authListOp = Optional.ofNullable((List<GrantedAuthority>) authentication.getAuthorities());
        }catch (NullPointerException e) {
            log.info("authentication.getAuthorities() -> Null");
            return false;
        }

        if(authListOp.isEmpty()) {
            return false;
        }else {
            List<GrantedAuthority> authList = authListOp.get();
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

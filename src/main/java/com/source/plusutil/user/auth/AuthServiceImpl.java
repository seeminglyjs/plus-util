package com.source.plusutil.user.auth;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.user.UserInfoService;
import com.source.plusutil.user.dto.UserInfoDto;
import com.source.plusutil.user.dto.UserPayloadDto;
import com.source.plusutil.user.jwt.JwtService;
import com.source.plusutil.utils.auth.dto.AuthObject;
import com.source.plusutil.utils.encrypt.AesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final JwtService jwtService;

    private final PropertiesConfig config;

    @Override
    public AuthObject makeAuthObject(Cookie token, Cookie userName, HttpServletResponse response) {
        if((token == null) || (userName == null) ){
            log.info("===== [EMPTY] makeAuthObject request info is empty");
            return AuthObject.builder().authenticated(false).userNo(-1).build();
        }else{
            log.info("===== [INFO] Request User Token -> " + token.getValue());
            try{
                String userEmail = AesUtil.aes256DecryptMultiple(config.getAes256key(), config.getAes256iv(), userName.getValue(), 2).get("decryptContent");
                String jwtToken = AesUtil.aes256Decrypt(config.getAes256key(), config.getAes256iv(), token.getValue()).get("decryptContent");

                boolean tokenValidationCheck = jwtService.isTokenValidate(jwtToken, userEmail);
                if(tokenValidationCheck){
                    UserPayloadDto userPayloadDto = (UserPayloadDto) jwtService.extractClaims(jwtToken, claims -> claims.get("userInfo"));
                    return AuthObject.builder()
                            .authenticated(true)
                            .userNo(userPayloadDto.getUserNo())
                            .userEmail(userEmail)
                            .userRole(userPayloadDto.getUserRole())
                            .build();
                }else{
                    log.info("===== [FAIL] Jwt Validation Check Fail");
                    cookieExpire(token, response);
                    cookieExpire(userName, response);
                    return AuthObject.builder().authenticated(false).userNo(-1).build();
                }
            }catch (Exception e){
                log.info("===== [ERROR] Jwt Validation Exception -> " + e.getMessage());
                return AuthObject.builder().authenticated(false).userNo(-1).build();
            }

        }
    }

    public void cookieExpire(Cookie cookie, HttpServletResponse response){
        log.info("===== [INFO] cookieExpire {}", cookie.toString());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

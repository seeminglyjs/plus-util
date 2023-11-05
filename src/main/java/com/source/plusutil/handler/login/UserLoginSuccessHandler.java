package com.source.plusutil.handler.login;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.enums.returnUrl.HomeReturnUrl;
import com.source.plusutil.user.UserInfoService;
import com.source.plusutil.user.dto.NewTokenRequestDto;
import com.source.plusutil.user.dto.UserInfoDto;
import com.source.plusutil.user.dto.UserPayloadDto;
import com.source.plusutil.user.jwt.JwtService;
import com.source.plusutil.utils.encrypt.AesUtil;
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
public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;

    private final PropertiesConfig config;

    private final UserInfoService userInfoService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("===== [RESULT] [UserLogin Success] =====");

        // IP, 세션 ID
        WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
        log.info("IP : " + web.getRemoteAddress());
//        log.info("Session ID : " + web.getSessionId());

        String loginUserName = authentication.getName();
        UserInfoDto userInfoDto = (UserInfoDto) userInfoService.loadUserByUsername(loginUserName);

        // 현재 시간을 얻어옵니다.
        long currentTimeMillis = System.currentTimeMillis();
        // 24시간을 밀리초 단위로 계산합니다.
        long twentyFourHoursInMillis = 24 * 60 * 60 * 1000;
        // 현재 시간에 24시간을 더한 시간을 계산합니다.
        long twentyFourHoursLaterMillis = currentTimeMillis + twentyFourHoursInMillis;
        int maxAgeInSeconds = (int) (twentyFourHoursInMillis / 1000); // 밀리초를 초로 변환

        // 인증 ID
        log.info("name : " + loginUserName);
        if(loginUserName == null || loginUserName.isEmpty()){
            log.info("===== [ERROR] Login user id is Empty");
        }else{
            // 권한
            log.info("===== [ROLE] login Request User Role -> " + userInfoDto.getUserRole());

            Cookie userNameCookie = new Cookie(
                    "userName",
                    AesUtil.aes256EncryptMultiple(config.getAes256key(), config.getAes256iv(), loginUserName, 2).get("encryptContent")); //userName 두번 암호화

            userNameCookie.setMaxAge(maxAgeInSeconds); //수명지정하기 하루로 설정
            userNameCookie.setPath("/"); //모든 url 적용
            response.addCookie(userNameCookie);

            Map<String, Object> payLoadMap = new HashMap<>();
            payLoadMap.put("userInfo", UserPayloadDto
                    .builder()
                    .userNo(userInfoDto.getUserNo())
                    .userRole(userInfoDto.getUserRole())
                    .build()
            );

            log.info("===== [MAKE] Login Success -> Make The Jwt Token! ");
            NewTokenRequestDto newTokenRequestDto = NewTokenRequestDto
                    .builder()
                    .userName(loginUserName)
                    .payLoad(payLoadMap)
                    .IssueDate(new Date(currentTimeMillis))
                    .ExpirationDate(new Date(twentyFourHoursLaterMillis)) // 만료시간 하루 지정
                    .build();
            String userTokenStr = jwtService.generateToken(
                    newTokenRequestDto
            );
            log.info("===== [INFO] Origin Token -> " + userTokenStr);
            Cookie userTokenCookie = new Cookie("userToken", AesUtil.aes256Encrypt(config.getAes256key(), config.getAes256iv(), userTokenStr).get("encryptContent"));
            userTokenCookie.setMaxAge(maxAgeInSeconds);
            userTokenCookie.setPath("/");
            response.addCookie(userTokenCookie);
            log.info("===== [ADD] Add Encrypt Token in a Cookie -> " + userTokenCookie.getValue());
            setDefaultTargetUrl(HomeReturnUrl.NEXT_HOME_MAIN.getUrl()); //로그인 성공시 홈페이지로 보낸다.
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

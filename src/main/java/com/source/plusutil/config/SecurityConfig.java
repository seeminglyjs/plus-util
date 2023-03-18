package com.source.plusutil.config;

import com.source.plusutil.enums.returnUrl.HomeReturnUrl;
import com.source.plusutil.enums.returnUrl.LoginReturnUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.source.plusutil.handler.login.UserLoginFailureHandler;
import com.source.plusutil.handler.login.UserLoginSuccessHandler;
import com.source.plusutil.handler.login.UserLogoutSuccessHandler;
import com.source.plusutil.service.userService.UserInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 또는 @Nonnull 이 붙은 필드의 생성자를 자동으로 생성
@EnableWebSecurity //spring security를 활성화 한다. //@Configuration이 포함되어 있음
@EnableGlobalMethodSecurity(prePostEnabled = true) //인가여부 처리 [ 메소드 호출 이전 이후에 권한을 확인할 수 있다.]
public class SecurityConfig {

    private final UserLoginSuccessHandler userLoginSuccessHandler; //로그인 성공 핸들러
    private final UserLoginFailureHandler userLoginFailureHandler; //로그인 실패 핸들러
    private final UserInfoService userInfoService;
    //	private final UserInfoService userInfoService;
    //	어떠한 빈(Bean)에 생성자가 오직 하나만 있고,
    //	생성자의 파라미터 타입이 빈으로 등록 가능한 존재라면
    //	이 빈은 @Autowired 어노테이션 없이도 의존성 주입이 가능하다.


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //스프링이 인증이 필요한 시점에 AuthenticationManager 을 호출한다.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    //로그인 정보를가로체 계정 정보를 가져온다. 위에있는 내용으로 변경됨 
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()//보호된 리소스 URI에 접근할 수 있는 권한을 설정
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(
                        "/plus/login/**"
                        ,"/plus/home"
                        , "/plus/join/**"
                        , "/plus/encrypt/**"
                        , "/plus/util/**"
                        , "/plus/notice/**"
                        , "/plus/algorithm/**"
                        , "/plus/error/main"
                        , "/plus/fun/**"
                        , "/plus/test/**"
                        , "/plus/auth/**"
                ).permitAll() //누구나 접근가능한 페이지 적용
                .antMatchers("/plus/setting/**", "/plus/admin/**").hasRole("ADMIN") //관리자(Admin)만 접근 허용
                .antMatchers("/plus/user/**", "/plus/logout/**").hasAnyRole("USER", "ADMIN") //유저(USER) / 관리자(Admin)만 접근 허용
                .anyRequest().authenticated() //나머지 경로에 대해서는 인가된 사용자만 접근할 수 있다.
                .and()
                .formLogin() //html form 형식으로 요청을 받아적용하겠다.
                .loginPage(LoginReturnUrl.NEXT_LOGIN_MAIN.getUrl()) //로그인페이지
                .loginProcessingUrl("/plus/login/action") //스프링 시큐리티가 로그인 검증을 하는 url
                .usernameParameter("userEmail") //로그인 파라미터중 username으로 적용할 파라미터의 이름
                .passwordParameter("userPassword") //로그인 파라미터중 password으로 적용할 파라미터의 이름
                .successHandler(userLoginSuccessHandler) //로그인 성공시 동작할 핸들러
                .failureHandler(userLoginFailureHandler) //로그인 실패시 동작할 핸들러

                .and()
                .logout()
                .logoutUrl("/plus/logout") //로그아웃 url 명시
                .logoutSuccessHandler(logoutSuccessHandler())//로그아웃 성공 시 동작할 핸들러

                .and()
                .csrf()//csrf 보안 설정을 비활성화
                .disable();//해당 기능을 사용하기 위해서는 프론트단에서 csrf토큰값 보내줘야함 // Cross site Request forgery로 사이즈간 위조 요청인데, 즉 정상적인 사용자가 의도치 않은 위조요청을 보내는 것을 의미한다.


        http.sessionManagement()
                .maximumSessions(1) // 최대 접속수를 1개로 제한한다. 다른 사용자가 로그인하면 이전 사용자 로그인 풀림
                .expiredUrl(LoginReturnUrl.NEXT_LOGIN_MAIN.getUrl()); // 세션이 제한 되었을 경우 리다이렉트 할 URL

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(3600) //초단위로 설정가능
                .alwaysRemember(false) // remember-me가 활성화 안되어도 되게하는 설정 default 가 false
                .userDetailsService(userInfoService); //인증객체 정보 저장


        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean //제외시킬 경로들
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/images/**", "/js/**", "/html/**", "/assets/**", "/css/**", "/bootstrap-icons-1.10.3/**");
    }
    @Bean // 로그 아웃이 성공했을 때 동작하는 핸들러
    public LogoutSuccessHandler logoutSuccessHandler() {
        UserLogoutSuccessHandler logoutSuccessHandler = new UserLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl(HomeReturnUrl.NEXT_HOME_MAIN.getUrl());
        return logoutSuccessHandler;
    }
}

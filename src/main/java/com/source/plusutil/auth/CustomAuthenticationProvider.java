package com.source.plusutil.auth;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.user.UserInfoService;
import com.source.plusutil.utils.encrypt.AesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserInfoService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final PropertiesConfig config;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username =  AesUtil.aes256Decrypt(config.getAes256key(), config.getAes256iv(),authentication.getName()).get("decryptContent");
        String password = AesUtil.aes256Decrypt(config.getAes256key(), config.getAes256iv(),authentication.getCredentials().toString()).get("decryptContent");
        log.info("username -> " + username);
        log.info("password -> " + password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
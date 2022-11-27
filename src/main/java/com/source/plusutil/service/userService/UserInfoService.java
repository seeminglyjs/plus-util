package com.source.plusutil.service.userService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.source.plusutil.dto.userDto.UserInfoDto;
import com.source.plusutil.handler.login.UserLoginFailureHandler;
import com.source.plusutil.handler.login.UserLoginSuccessHandler;
import com.source.plusutil.repository.userRepository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoService implements UserDetailsService{

	private final UserInfoRepository userInfoRepository;
	
	@Override //로그인한 사용자 정보를 가져온다.
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		log.info("[loadUserByUsername Start] =====");
		log.info("loadUserByUsername param info -> [" + userEmail + "]");
		Optional<UserInfoDto> userInfoOptional = userInfoRepository.findByUserEmail(userEmail); //요청아이디(이메일) 기준으로 유저정보를 찾는다.
		UserInfoDto userInfo = userInfoOptional.orElse(null); //null 여부 체크
		if(userInfo == null) {
			log.info("loadUserByUsername UserInfoDto -> [ null ] ");
			log.info("throw new UsernameNotFoundException -> [" + userEmail + "] ");
			throw new UsernameNotFoundException(userEmail); //예외던지기
		}else {
			log.info("loadUserByUsername UserInfoDto -> [" + userInfo.toString() + "] ");
		}
        return userInfo;	
	}

}

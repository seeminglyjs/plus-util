package com.source.plusutil.user;

import java.util.Optional;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.utils.encrypt.AesUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.source.plusutil.user.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoService implements UserDetailsService{

	private final UserInfoRepository userInfoRepository;

	private final PropertiesConfig config;

	@Override //로그인한 사용자 정보를 가져온다.
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		log.info("[loadUserByUsername Start] =====");
		log.info("loadUserByUsername param info -> [" + userEmail + "]");

		Optional<UserInfoDto> userInfoOptional = userInfoRepository.findByUserEmail(userEmail);
		UserInfoDto userInfo = userInfoOptional.orElse(null);

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

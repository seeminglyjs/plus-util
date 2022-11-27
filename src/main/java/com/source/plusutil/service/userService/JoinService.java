package com.source.plusutil.service.userService;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.dto.userDto.UserInfoDto;
import com.source.plusutil.dto.userDto.UserJoinDto;
import com.source.plusutil.repository.userRepository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class JoinService {

	@Autowired
	PropertiesConfig config;

	@Autowired
	PasswordEncoder passwordEncoder;

	//생성자 객체 주입
	private final UserInfoRepository userInfoRepository;	

	/**
	 * 회원가입 유저 정보 저장 메소드
	 * 
	 * @param userEmail
	 * @param userPassword
	 * @param request
	 */
	public void joinAction(UserJoinDto userJoinDto, HttpServletRequest request) {
		boolean joinSuccess = false; //회원가입 성공여부 체크 변수
		log.info("joinAction UserJoinDto ->["+userJoinDto.toString()+"]");
		if(joinDataCheck(userJoinDto.getUserEmail()) && joinDataCheck(userJoinDto.getUserPassword())) {//정해진 데이터가 모두 정상인 경우
			log.info("userEmail -> [" + userJoinDto.getUserEmail() + "]");
			log.info("userPassword -> [" + userJoinDto.getUserPassword() + "]");
			if(validateDuplicateUser(userJoinDto.getUserEmail())) {//유저 로그인 계정 중복 여부 체크		
				try{
					userInfoRepository.save(UserInfoDto.makeUser(userJoinDto, passwordEncoder)); //만들어진 유저정보 db 저장하기
					joinSuccess = true;//에외없이 처리되면 회원가입 성공
				}catch (Exception e) {
					log.info("[joinDataVaildCheck false] =====");
					log.info("[Exception info]", e);
				}
			}
		}
		//회원가입 성공여부 객체 저장
		request.setAttribute("joinSuccess", joinSuccess);
	}

	/**
	 * 회원가입 유저정보의 중복 여부를 체크하는 메소드
	 * 
	 * @param userEmail
	 * @return
	 */
	private boolean validateDuplicateUser(String userEmail) {
		Optional<UserInfoDto> userInfoDtoOptinoal = userInfoRepository.findByUserEmail(userEmail);
		UserInfoDto userInfo = userInfoDtoOptinoal.orElse(null); //null 여부 체크
		if(userInfo == null) {
			log.info("validateDuplicateUser UserInfoDto -> [ null ] ");
			log.info("[joinAction available] ===== ");
			return true;
		}else {
			log.info("validateDuplicateUser exist -> [" + userInfo.toString() + "] ");
			return false;
		}		 
	}

	/**
	 * 회원가입 정보가 유효한지 여부를 체크하는 메소드
	 * 
	 * @param data
	 * @return
	 */
	public boolean joinDataCheck(String data) {
		boolean check = true;
		//요청데이터가 null이거난 없을 경우 false
		if(data == null || data.equals(config.getNoData())) {
			check = false;
		}
		return check;
	}
	
	/**
	 * 회원가입 실패시에 동작할 메소드
	 * 
	 * @param request
	 */
	public void joinFail(HttpServletRequest request) {
		log.info("joinFail Method start =====");
		boolean joinSuccess = false;
		request.setAttribute("joinSuccess", joinSuccess);
	}
}

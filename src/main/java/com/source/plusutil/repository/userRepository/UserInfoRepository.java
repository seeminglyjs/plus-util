package com.source.plusutil.repository.userRepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.source.plusutil.dto.userDto.UserInfoDto;

public interface UserInfoRepository extends JpaRepository<UserInfoDto, String>{
	
	/**
	 * 사용자 이메일[아이디] 기준으로 유저 정보를 찾는다.
	 * 
	 * @param userEmail
	 * @return
	 */
	public Optional<UserInfoDto> findByUserEmail(String userEmail);

}

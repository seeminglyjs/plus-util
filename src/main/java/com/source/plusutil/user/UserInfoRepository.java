package com.source.plusutil.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.source.plusutil.user.dto.UserInfoDto;

public interface UserInfoRepository extends JpaRepository<UserInfoDto, Integer>{ //제네릭 안에 DTO 객체와 PK 타입을 적어준다.
	
	/**
	 * 사용자 이메일[아이디] 기준으로 유저 정보를 찾는다.
	 * 
	 * @param userEmail
	 * @return
	 */
	public Optional<UserInfoDto> findByUserEmail(String userEmail);

}

package com.source.plusutil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.source.plusutil.dto.TestInfo;
import com.source.plusutil.repository.TestInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestService {

	@Autowired
	TestInfoRepository testInfoRepository;
	
	//테스트 유저정보 test1을 가져온다.
	public Optional<TestInfo> selectTestUserid() {
		log.info("Test User test1 select ====");
		return testInfoRepository.findById("test1");
	}
}

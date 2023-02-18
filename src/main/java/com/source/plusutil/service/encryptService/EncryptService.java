package com.source.plusutil.service.encryptService;

import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.dto.encrypt.Aes256EncryptRequestDto;
import com.source.plusutil.dto.encrypt.RsaDecryptRequestDto;
import com.source.plusutil.dto.encrypt.RsaEncryptRequestDto;
import com.source.plusutil.utils.encrypt.Aes256Util;
import com.source.plusutil.utils.encrypt.RsaUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EncryptService {

	//컨피그 정보 연결하기
	private final PropertiesConfig config;
	
	private final Aes256Util aes256;
	private final RsaUtil rsa;
	
	/**
	 * 자릿수를 전달받아 랜덤한 문자열을 만드는 메소드
	 * 
	 * @param stringLength
	 * @return
	 */
	public String makeRandomString(int stringLength) {
		String radomString;
		boolean useLetters = true; //문자사용여부
		boolean useNumbers = true; //숫자사용여부
		radomString = RandomStringUtils.random(stringLength, useLetters, useNumbers);
		return radomString;
	}

	/*
	 * 요청 정보를 기준으로 aes256 암호화 정보를 만들어서 리턴한다.
	 * 
	 * @param aes256EncryptRequestDto
	 * @param request 
	 */
	public void makeAse256EncryptContent(Aes256EncryptRequestDto aes256EncryptRequestDto, HttpServletRequest request) {
		String encryptContent = "";
		log.info("aes256EncryptRequestDto info -> [" + aes256EncryptRequestDto.toString() +"]");
		//객체파라미터 정보를 가지고 암호화 시작
		encryptContent = aes256.aes256Encrypt(aes256EncryptRequestDto.getAes256Key()
				, aes256EncryptRequestDto.getAes256Iv()
				, aes256EncryptRequestDto.getAes256Content());
		log.info("encryptContent ->["+ encryptContent +"]");

		request.setAttribute("aes256Key", aes256EncryptRequestDto.getAes256Key());
		request.setAttribute("aes256Iv", aes256EncryptRequestDto.getAes256Iv());
		request.setAttribute("aes256Content", aes256EncryptRequestDto.getAes256Content());
		//파라미터 전달
		request.setAttribute("encryptContent", encryptContent);

	}

	/*
	 * 요청 정보를 기준으로 aes256로 암호화된 정보를 복호화 한다.
	 * 
	 * @param aes256Content
	 * @param aes256Content2 
	 * @param aes256Iv 
	 * @param request 
	 * @return
	 */
	public void makeAse256DecryptContent(String aes256Key, String aes256Iv, String aes256Content, HttpServletRequest request) {
		String decryptContent = "";
		decryptContent = aes256.aes256Decrypt(aes256Key, aes256Iv, aes256Content);
		log.info("encryptContent ->["+ decryptContent +"]");
		
		//파라미터 전달
		request.setAttribute("decryptContent", decryptContent);
	}

	/**
	 * rsa 키를 만드는 메소드
	 * @param request 
	 * 
	 * @return
	 */
	public void makeRasKey(HttpServletRequest request) {
		HashMap<String,String> keyInfo =  rsa.createKeypairAsString();
		if(keyInfo != null) {
			String publicKey = keyInfo.get("publicKey");
			String privateKey = keyInfo.get("privateKey");
			request.setAttribute("publicKey", publicKey);
			request.setAttribute("privateKey", privateKey);
		}
	}
	
	/**
	 * rsa 공개키 기준으로 암호화 하는 메소드
	 * 
	 * @param rsaEncryptRequestDto
	 * @param request
	 */
	public void makeRsaEncryptContent(RsaEncryptRequestDto rsaEncryptRequestDto, HttpServletRequest request) {
		String encryptContent = "";
		Optional<String> tempData = Optional.ofNullable(rsa.encode(rsaEncryptRequestDto.getContent(), rsaEncryptRequestDto.getRsaPublicKey()));
		if(tempData.isPresent()) {
			encryptContent = tempData.get();
			request.setAttribute("encryptContent", encryptContent);
		}
	}

	public void makeRsaDecryptContent(RsaDecryptRequestDto rsaDecryptRequestDto, HttpServletRequest request) {
		String decryptContent = "";
		Optional<String> tempData = Optional.ofNullable(rsa.decode(rsaDecryptRequestDto.getContent(), rsaDecryptRequestDto.getRsaPrivateKey()));
		if(tempData.isPresent()) {
			decryptContent = tempData.get();
			request.setAttribute("decryptContent", decryptContent);
		}
	}
}

package com.source.plusutil.utils.encrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.source.plusutil.config.PropertiesConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class Aes256Util {

	private final PropertiesConfig config;
	
	/**
	 * aes256 기준 암호화 하는 메소드
	 * 
	 * @param aesKey
	 * @param aesIv
	 * @param content
	 * @return
	 */
	public String aes256Encrypt(String aesKey, String aesIv, String content) {
		String encryptContent = "";

		//aes256 알고리즘 정보 연결
		String alg = config.getAes256Alg();
		
		log.info("alg -> [" + alg + "] aesKey -> [" + aesKey + "] aesIv -> [" + aesIv + "] content -> [" + content + "]");
		
		//알고리즘 aes-256 **********[암호화]**********
		try {
			Cipher cipher = Cipher.getInstance(alg);
			//키로 비밀키 생성
			SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
			//iv 로 spec 생성
			IvParameterSpec ivParamSpec = new IvParameterSpec(aesIv.getBytes());
			//암호화 적용
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

			//암호화 실행
			byte[] encrypted1 = cipher.doFinal(content.getBytes("UTF-8")); // ID 암호화(인코딩 설정)
			encryptContent = Base64.getEncoder().encodeToString(encrypted1); // 암호화 인코딩 후 저장

			log.info("encrypt Info -> [" + encryptContent + "] ");

		}catch (Exception e) {
			log.info("[aes256Encrypt exception]",e);
		}
		return encryptContent;
	}
	
	/**
	 * 암호화 기준 복호화 하는 메소드
	 * 
	 * @param aesKey
	 * @param aesIv
	 * @param content
	 * @return
	 */
	public String aes256Decrypt(String aesKey, String aesIv, String content) {
		String decryptContent = "";

		//aes256 알고리즘 정보 연결
		String alg = config.getAes256Alg();
		
		log.info("alg -> [" + alg + "] aesKey -> [" + aesKey + "] aesIv -> [" + aesIv + "] content -> [" + content + "]");

		//----암호화 해석 코드 **********[복호화]**********
		try {
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(aesIv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
			
				//암호 해석
				byte[] decodedBytes = Base64.getDecoder().decode(content);
				byte[] decrypted = cipher.doFinal(decodedBytes);
				decryptContent = new String(decrypted); //바이트 정보 스트링으로 변경
			
				log.info("Decrypt Info -> [" + decryptContent + "] ");

		}catch (Exception e) {
			log.info("[aes256Decrypt exception]",e);
		}
		//---------------------------------------------------------------------------
		return decryptContent;
	}
}

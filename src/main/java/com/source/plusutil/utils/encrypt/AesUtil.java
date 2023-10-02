package com.source.plusutil.utils.encrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AesUtil {

	/*
	 * aes256 기준 암호화 하는 메소드
	 * 
	 * @param aesKey
	 * @param aesIv
	 * @param content
	 * @return
	 */
	public static Map<String, String> aes256Encrypt(String aesKey, String aesIv, String content) {
		Map<String, String> returnMap = new HashMap<>();
		String encryptContent = "";

		//aes256 알고리즘 정보 연결
		String alg = "AES/CBC/PKCS5Padding";
		
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
			returnMap.put("result", "n");
			returnMap.put("encryptContent", encryptContent);
			returnMap.put("message", returnError(e));
			return returnMap;
		}
		returnMap.put("result", "y");
		returnMap.put("encryptContent", encryptContent);
		returnMap.put("message", "success");
		return returnMap;
	}
	
	/*
	 * 암호화 기준 복호화 하는 메소드
	 * 
	 * @param aesKey
	 * @param aesIv
	 * @param content
	 * @return
	 */
	public static Map<String, String> aes256Decrypt(String aesKey, String aesIv, String content) {
		Map<String, String> returnMap = new HashMap<>();
		String decryptContent = "";

		//aes256 알고리즘 정보 연결
		String alg = "AES/CBC/PKCS5Padding";
		
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
			log.info("[aesDecrypt exception]",e);
			returnMap.put("result", "n");
			returnMap.put("decryptContent", decryptContent);
			returnMap.put("message", returnError(e));
			return returnMap;
		}
		//---------------------------------------------------------------------------
		returnMap.put("result", "y");
		returnMap.put("decryptContent", decryptContent);
		returnMap.put("message", "success");
		return returnMap;
	}


	public static String returnError(Exception e){
		return "에러가 발생했습니다. [" + e.getMessage() +"]";
	}
}

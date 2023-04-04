package com.source.plusutil.encrypt;


import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.encrypt.dto.AesDecryptRequestDto;
import com.source.plusutil.encrypt.dto.AesDecryptResponseDto;
import com.source.plusutil.encrypt.dto.AesEncryptRequestDto;
import com.source.plusutil.encrypt.dto.AesEncryptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.source.plusutil.dto.encrypt.RsaDecryptRequestDto;
import com.source.plusutil.dto.encrypt.RsaEncryptRequestDto;
import com.source.plusutil.service.encryptService.EncryptServiceImpl;

@RequestMapping("/plus/encrypt")
@RestController
@RequiredArgsConstructor
public class EncryptController {

	private final EncryptServiceImpl encryptService;
	
	/*
	 * 요청 받은 정보를 aes256으로 암호화한다.
	 */
	@PostMapping("/aes256/encrypt")
	@ResponseBody
	public AesEncryptResponseDto ase256Encrypt(@RequestBody AesEncryptRequestDto aesEncryptRequestDto) {
		return encryptService.makeAseEncryptContent(aesEncryptRequestDto);
	}
	/*
	 * 요청 받은 정보를 aes256으로 복호화한다.
	 */
	@PostMapping("/aes256/decrypt")
	@ResponseBody
	public AesDecryptResponseDto ase256Decrypt(@RequestBody AesDecryptRequestDto aesDecryptRequestDto) {
		return encryptService.makeAesDecryptContent(aesDecryptRequestDto);
	}
	
	/**
	 * rsa 암호화 메인 페이지
	 * 
	 * @return
	 */
	@RequestMapping("/rsa/main")
	public String rsaMain() {
		return "/encrypt/rsa/rsaMain";
	}
	
	
	/*
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/rsa/key/make/main")
	public String rsaKeyMakeMain() {
		return "/encrypt/rsa/rsaKeyMake";
	}
	
	
	@RequestMapping("/rsa/key/make")
	public String rsaKeyMake(HttpServletRequest request) {
		encryptService.makeRasKey(request);
		return "/encrypt/rsa/rsaKeyMake";
	}
	
	
	@RequestMapping("/rsa/content/encrypt/main")
	public String rsaContentEncrpytMain() {
		return "/encrypt/rsa/rsaContentEncrypt";
	}
	
	/**
	 * RSA 암호화를 진행하는 컨트롤러
	 * 
	 * @param rsaPublicKey
	 * @param rsaBeforeContent
	 * @param request
	 * @return
	 */
	@RequestMapping("/rsa/content/encrypt")
	public String rsaContentEncrpyt(@RequestParam String rsaPublicKey, @RequestParam  String rsaBeforeContent, HttpServletRequest request) {
		RsaEncryptRequestDto rsaEncryptRequestDto = new RsaEncryptRequestDto();
		rsaEncryptRequestDto.setContent(rsaBeforeContent);
		rsaEncryptRequestDto.setRsaPublicKey(rsaPublicKey);
		encryptService.makeRsaEncryptContent(rsaEncryptRequestDto, request);
		return "/encrypt/rsa/rsaContentEncrypt";
	}
	
	@RequestMapping("/rsa/content/decrypt/main")
	public String rsaContentDecrpytMain() {
		return "/encrypt/rsa/rsaContentDecrypt";
	}
		
	/**
	 * RSA 복호화를 진행하는 컨트롤러
	 * 
	 * @param rsaPrivateKey
	 * @param rsaAfterContent
	 * @param request
	 * @return
	 */
	@RequestMapping("/rsa/content/decrypt")
	public String rsaContentDecrpyt(@RequestParam String rsaPrivateKey,@RequestParam String rsaAfterContent, HttpServletRequest request) {
		RsaDecryptRequestDto rsaEncryptRequestDto = new RsaDecryptRequestDto();
		rsaEncryptRequestDto.setRsaPrivateKey(rsaPrivateKey);
		rsaEncryptRequestDto.setContent(rsaAfterContent);
		encryptService.makeRsaDecryptContent(rsaEncryptRequestDto, request);
		return "/encrypt/rsa/rsaContentDecrypt";
	}
	
}

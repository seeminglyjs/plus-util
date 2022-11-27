package com.source.plusutil.controller.encryptController;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.dto.encrypt.Aes256EncryptRequestDto;
import com.source.plusutil.dto.encrypt.RsaDecryptRequestDto;
import com.source.plusutil.dto.encrypt.RsaEncryptRequestDto;
import com.source.plusutil.service.encryptService.EncryptService;

@Controller
@RequestMapping("/encrypt")
public class EncryptController {

	@Autowired
	EncryptService encryptService;
	
	
	@GetMapping("/main")
	public String encryptMain() {
		return "/encrypt/encryptMain";
	}
	
	
	/**
	 * aes256 메인 접속 url
	 * 
	 * @return
	 */
	@GetMapping("/aes256/main")
	public String ase256Main() {
		return "/encrypt/aes256/aes256Main";
	}
	
	@RequestMapping("/aes256/encrypt/main")
	public String ase256EncryptMain() {	
		String url = "/encrypt/aes256/aes256Encrypt";		
		return url;
	}	
	
	/**
	 * 요청 받은 정보를 aes256으로 암호화한다.
	 * 
	 * @param aes256Key
	 * @param aes256Iv
	 * @param aes256Content
	 * @param request
	 * @return
	 */
	@RequestMapping("/aes256/encrypt")
	public String ase256Encrypt(@RequestParam String aes256Key, String aes256Iv, String aes256Content
			,HttpServletRequest request) {
		Aes256EncryptRequestDto aes256EncryptRequestDto = new Aes256EncryptRequestDto();
		aes256EncryptRequestDto.setAes256Key(aes256Key);
		aes256EncryptRequestDto.setAes256Iv(aes256Iv);
		aes256EncryptRequestDto.setAes256Content(aes256Content);		
		String url = "/encrypt/aes256/aes256Encrypt";		
		encryptService.makeAse256EncryptContent(aes256EncryptRequestDto, request);	
		return url;
	}
	
	
	@RequestMapping("/aes256/decrypt/main")
	public String ase256DecryptMain() {
		String url = "/encrypt/aes256/aes256Decrypt";		
		return url;
	}
	
	/**
	 * 요청 받은 정보를 aes256으로 복호화한다.
	 * 
	 * @param aes256Key
	 * @param aes256Iv
	 * @param aes256Content
	 * @param request
	 * @return
	 */
	@RequestMapping("/aes256/decrypt")
	public String ase256Decrypt(@RequestParam String aes256Key, String aes256Iv, String aes256Content
			,HttpServletRequest request) {
		String url = "/encrypt/aes256/aes256Decrypt";
		encryptService.makeAse256DecryptContent(aes256Key, aes256Iv, aes256Content, request);		
		return url;
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
	
	
	/**
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
	public String rsaContentEncrpyt(@RequestParam String rsaPublicKey, String rsaBeforeContent, HttpServletRequest request) {
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
	public String rsaContentDecrpyt(@RequestParam String rsaPrivateKey, String rsaAfterContent, HttpServletRequest request) {
		RsaDecryptRequestDto rsaEncryptRequestDto = new RsaDecryptRequestDto();
		rsaEncryptRequestDto.setRsaPrivateKey(rsaPrivateKey);
		rsaEncryptRequestDto.setContent(rsaAfterContent);
		encryptService.makeRsaDecryptContent(rsaEncryptRequestDto, request);
		return "/encrypt/rsa/rsaContentDecrypt";
	}
	
}

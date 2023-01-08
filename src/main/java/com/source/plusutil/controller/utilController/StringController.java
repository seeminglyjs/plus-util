package com.source.plusutil.controller.utilController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plusutil.service.utilService.stringUtil.StringUtilServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/util")
@RequiredArgsConstructor
public class StringController {

	private final StringUtilServiceImpl stringUtilServiceImpl;
	
	@GetMapping("/string/main")
	public String stringMain(HttpServletRequest request, Authentication authentication) {
		return "/util/string/stringMain.html";
	}
	
	@GetMapping("/string/get/byte/main")
	public String stringGetByteMain(HttpServletRequest request, Authentication authentication) {
		return "/util/string/getByteMain.html";
	}
	
	@PostMapping("/string/get/byte/action")
	public String stringGetByteAction(HttpServletRequest request, Authentication authentication, String stringContent, String encoding, String emptyYn) {
		stringUtilServiceImpl.getStringByte(request, stringContent, encoding, emptyYn);
		return "/util/string/getByteMain.html";
	}
	
	@GetMapping("/string/get/initial/main")
	public String stringGetInitialMain(HttpServletRequest request, Authentication authentication) {
		return "/util/string/getStringInitial.html";
	}
	
	@PostMapping("/string/get/initial/action")
	public String stringGetInitialAction(HttpServletRequest request, Authentication authentication, String stringContent) {
		stringUtilServiceImpl.getInitialString(request, stringContent);
		return "/util/string/getStringInitial.html";
	}
}

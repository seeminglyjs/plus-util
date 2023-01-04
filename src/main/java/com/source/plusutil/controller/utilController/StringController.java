package com.source.plusutil.controller.utilController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/util")
@RequiredArgsConstructor
public class StringController {

	@GetMapping("/string/main")
	public String stringMain(HttpServletRequest request, Authentication authentication) {
		return "/util/string/stringMain.html";
	}
	
	@GetMapping("/string/get/byte/main")
	public String stringGetByteMain(HttpServletRequest request, Authentication authentication) {
		return "/util/string/getByteMain.html";
	}
	
	@PostMapping("/string/get/byte/action")
	public String stringGetByteAction(HttpServletRequest request, Authentication authentication, String stringContent) {
		return "/util/string/getByteMain.html";
	}
}

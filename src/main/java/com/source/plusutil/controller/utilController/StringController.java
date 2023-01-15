package com.source.plusutil.controller.utilController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.plusutil.service.utilService.stringUtil.StringUtilServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/util")
@RequiredArgsConstructor
public class StringController {

	private final StringUtilServiceImpl stringUtilServiceImpl;

	@GetMapping("/string/main")
	public String stringMain() {
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
	public String stringGetInitialMain(Authentication authentication) {
		return "/util/string/getStringInitial.html";
	}

	@PostMapping("/string/get/initial/action")
	public String stringGetInitialAction(HttpServletRequest request, Authentication authentication, String stringContent) {
		stringUtilServiceImpl.getInitialString(request, stringContent);
		return "/util/string/getStringInitial.html";
	}

	@GetMapping("/string/get/length/main")
	public String stringGetLengthMain(HttpServletRequest request, Authentication authentication, String stringContent) {
		return "/util/string/getLengthMain.html";
	}
	
	@PostMapping("/string/get/length/action")
	@ResponseBody
	public Map<String,String> stringGetLengthAction(HttpServletRequest request, Authentication authentication, String stringContent) {
		return stringUtilServiceImpl.getLengthString(request, stringContent);
	}

	@GetMapping("/string/convert/ual/main")
	public String stringConvertUpperAndLowerMain(HttpServletRequest request, Authentication authentication, String stringContent, String upperOrLower) {
		return "/util/string/convertUpperAndLower.html";
	}

	@PostMapping("/string/convert/ual/action")
	public String stringConvertUpperAndLowerAction(HttpServletRequest request, Authentication authentication, String stringContent, String upperOrLower) {
		stringUtilServiceImpl.convertUpperAndLowerMain(request, stringContent, upperOrLower);
		return "/util/string/convertUpperAndLower.html";
	}
}

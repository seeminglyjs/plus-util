package com.source.plusutil.controller.utilController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.enums.returnUrl.StringUtilReturnUrl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.plusutil.service.utilService.stringUtil.StringUtilServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/plus/util")
@RequiredArgsConstructor
public class StringController {

	private final StringUtilServiceImpl stringUtilServiceImpl;

	@GetMapping("/string/main")
	public String stringMain() {
		return StringUtilReturnUrl.STRING_MAIN.getUrl();
	}

	@GetMapping("/string/get/byte/main")
	public String stringGetByteMain(HttpServletRequest request, Authentication authentication) {
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_GET_BYTE_MAIN.getUrl();
	}

	@PostMapping("/string/get/byte/action")
	public String stringGetByteAction(HttpServletRequest request, Authentication authentication, String stringContent, String encoding, String emptyYn) {
		stringUtilServiceImpl.getStringByte(request, stringContent, encoding, emptyYn);
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_GET_BYTE_MAIN.getUrl();
	}

	@GetMapping("/string/get/initial/main")
	public String stringGetInitialMain(HttpServletRequest request, Authentication authentication) {
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_GET_STRING_INITIAL_MAIN.getUrl();
	}

	@PostMapping("/string/get/initial/action")
	public String stringGetInitialAction(HttpServletRequest request, Authentication authentication, String stringContent) {
		stringUtilServiceImpl.getInitialString(request, stringContent);
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_GET_STRING_INITIAL_MAIN.getUrl();
	}

	@GetMapping("/string/get/length/main")
	public String stringGetLengthMain(HttpServletRequest request) {
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_GET_LENGTH_MAIN.getUrl();
	}
	
	@PostMapping("/string/get/length/action")
	@ResponseBody
	public Map<String,String> stringGetLengthAction(HttpServletRequest request, String stringContent) {
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return stringUtilServiceImpl.getLengthString(request, stringContent);
	}

	@GetMapping("/string/convert/ual/main")
	public String stringConvertUpperAndLowerMain(HttpServletRequest request) {
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_CONVERT_UPPER_AND_LOWER_MAIN.getUrl();
	}

	@PostMapping("/string/convert/ual/action")
	public String stringConvertUpperAndLowerAction(HttpServletRequest request, String stringContent, String upperOrLower) {
		stringUtilServiceImpl.convertUpperAndLowerMain(request, stringContent, upperOrLower);
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_CONVERT_UPPER_AND_LOWER_MAIN.getUrl();
	}
	@GetMapping("/string/check/similarity/main")
	public String checkSimilarityMain(HttpServletRequest request){
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		return StringUtilReturnUrl.STRING_CHECK_SIMILARITY.getUrl();
	}
	@PostMapping("/string/check/similarity/action")
	public String checkSimilarityAction(HttpServletRequest request, String firstContent, String secondContent){
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		stringUtilServiceImpl.checkSimilarity(request,firstContent,secondContent);
		return StringUtilReturnUrl.STRING_CHECK_SIMILARITY.getUrl();
	}
}

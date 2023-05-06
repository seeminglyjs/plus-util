package com.source.plusutil.utilString;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.enums.returnUrl.StringUtilReturnUrl;
import com.source.plusutil.utilString.dto.StringConvertCaseRequestDto;
import com.source.plusutil.utilString.dto.StringConvertCaseResponseDto;
import com.source.plusutil.utilString.dto.StringInitialRequestDto;
import com.source.plusutil.utilString.dto.StringInitialResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/plus/util/string")
@RequiredArgsConstructor
public class StringController {

	private final StringUtilServiceImpl stringUtilServiceImpl;

	@PostMapping("/initial")
	@ResponseBody
	public StringInitialResponseDto stringGetInitialAction(@RequestBody StringInitialRequestDto stringInitialRequestDto) {
		return stringUtilServiceImpl.getInitialString(stringInitialRequestDto);
	}

	@PostMapping("/convert/case")
	@ResponseBody
	public StringConvertCaseResponseDto stringConvertUpperAndLowerAction(@RequestBody StringConvertCaseRequestDto stringConvertCaseRequestDto) {
		return stringUtilServiceImpl.convertUpperAndLowerMain(stringConvertCaseRequestDto);
	}

	@PostMapping("/string/check/similarity/action")
	public String checkSimilarityAction(HttpServletRequest request, String firstContent, String secondContent){
		request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
		stringUtilServiceImpl.checkSimilarity(request,firstContent,secondContent);
		return StringUtilReturnUrl.STRING_CHECK_SIMILARITY.getUrl();
	}
}

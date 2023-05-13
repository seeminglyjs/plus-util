package com.source.plusutil.utilString;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.enums.returnUrl.StringUtilReturnUrl;
import com.source.plusutil.utilString.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
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

	@PostMapping("/similarity")
	@ResponseBody
	public StringSimilarityResponseDto checkSimilarityAction(@RequestBody StringSimilarityRequestDto stringSimilarityRequestDto){
		return stringUtilServiceImpl.checkSimilarity(stringSimilarityRequestDto);
	}
}

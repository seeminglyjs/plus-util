package com.source.plusutil.utilString;

import com.source.plusutil.utilString.dto.*;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plus/util/string")
@RequiredArgsConstructor
public class StringController {

	private final StringUtilService stringUtilService;

	@PostMapping("/initial")
	@ResponseBody
	public StringInitialResponseDto stringGetInitialAction(@RequestBody StringInitialRequestDto stringInitialRequestDto) {
		return stringUtilService.getInitialString(stringInitialRequestDto);
	}

	@PostMapping("/convert/case")
	@ResponseBody
	public StringConvertCaseResponseDto stringConvertUpperAndLowerAction(@RequestBody StringConvertCaseRequestDto stringConvertCaseRequestDto) {
		return stringUtilService.convertUpperAndLowerMain(stringConvertCaseRequestDto);
	}

	@PostMapping("/similarity")
	@ResponseBody
	public StringSimilarityResponseDto checkSimilarityAction(@RequestBody StringSimilarityRequestDto stringSimilarityRequestDto){
		return stringUtilService.checkSimilarity(stringSimilarityRequestDto);
	}

	@PostMapping("/replace")
	@ResponseBody
	public StringReplaceResponseDto stringReplaceAction(@RequestBody StringReplaceRequestDto stringReplaceRequestDto){
		return stringUtilService.stringReplace(stringReplaceRequestDto);
	}
}

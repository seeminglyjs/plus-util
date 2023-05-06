package com.source.plusutil.utilString;

import com.source.plusutil.utilString.dto.StringConvertCaseRequestDto;
import com.source.plusutil.utilString.dto.StringConvertCaseResponseDto;
import com.source.plusutil.utilString.dto.StringInitialRequestDto;
import com.source.plusutil.utilString.dto.StringInitialResponseDto;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface StringUtilService {

	@Deprecated
	void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn);

	StringInitialResponseDto getInitialString(StringInitialRequestDto stringInitialRequestDto);

	@Deprecated
	Map<String,String> getLengthString(HttpServletRequest request, String stringContent);

	StringConvertCaseResponseDto convertUpperAndLowerMain(StringConvertCaseRequestDto stringConvertCaseRequestDto);

    void checkSimilarity(HttpServletRequest request, String firstContent, String secondContent);
}

package com.source.plusutil.utilString;

import com.source.plusutil.utilString.dto.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public sealed interface StringUtilService permits StringUtilServiceImpl{

	@Deprecated
	void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn);

	StringInitialResponseDto getInitialString(StringInitialRequestDto stringInitialRequestDto);

	@Deprecated
	Map<String,String> getLengthString(HttpServletRequest request, String stringContent);

	StringConvertCaseResponseDto convertUpperAndLowerMain(StringConvertCaseRequestDto stringConvertCaseRequestDto);

	StringSimilarityResponseDto checkSimilarity(StringSimilarityRequestDto stringSimilarityRequestDto);

    StringReplaceResponseDto stringReplace(StringReplaceRequestDto stringReplaceRequestDto);
}

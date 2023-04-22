package com.source.plusutil.utilString;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface StringUtilService {

	void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn);
	
	void getInitialString(HttpServletRequest request, String stringContent);
	
	Map<String,String> getLengthString(HttpServletRequest request, String stringContent);

	void convertUpperAndLowerMain(HttpServletRequest request, String stringContent, String upperOrLower);

    void checkSimilarity(HttpServletRequest request, String firstContent, String secondContent);
}

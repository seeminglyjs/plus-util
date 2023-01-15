package com.source.plusutil.service.utilService.stringUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface StringUtilService {

	public void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn);
	
	public void getInitialString(HttpServletRequest request, String stringContent);
	
	public Map<String,String> getLengthString(HttpServletRequest request, String stringContent);

	public void convertUpperAndLowerMain(HttpServletRequest request, String stringContent, String upperOrLower);

}

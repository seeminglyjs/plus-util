package com.source.plusutil.service.utilService.stringUtil;

import javax.servlet.http.HttpServletRequest;

public interface StringUtilService {

	public void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn);
	
	public void getInitialString(HttpServletRequest request, String stringContent);
}

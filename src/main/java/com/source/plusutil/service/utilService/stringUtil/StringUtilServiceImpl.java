package com.source.plusutil.service.utilService.stringUtil;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public class StringUtilServiceImpl implements StringUtilService{

	@Override
	public void getStringByte(HttpServletRequest request, String stringContent) {
		Optional<String> stringContentOp = Optional.ofNullable(stringContent);
		if(stringContentOp.isPresent()) {//전달 문자열 존재
			/**
			 * 바이트 계산로직 추가
			 * 
			 */
		}
	}
}

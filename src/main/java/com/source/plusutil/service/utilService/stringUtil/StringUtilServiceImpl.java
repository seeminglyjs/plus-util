package com.source.plusutil.service.utilService.stringUtil;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class StringUtilServiceImpl implements StringUtilService{

	static String [] INITIAL_STRING = {
			"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", 
			"ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", 
			"ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", 
			"ㅋ", "ㅌ", "ㅍ", "ㅎ" };

	/**
	 * 문자열 정보를 입력받아 바이트 수를 계산한다.
	 * 
	 */
	@Override
	public void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn) {
		String stringContentByte = "에러가 발생했습니다.";
		Optional<String> stringContentOp = Optional.ofNullable(stringContent);
		if(stringContentOp.isPresent()) {//전달 문자열 존재
			if(encoding == null) {encoding = "UTF-8";}
			if(emptyYn == null) {emptyYn = "No";}
			try {
				int ByteInfo = 0;
				if(emptyYn.equals("Yes")) {
					ByteInfo = stringContentOp.get()
							.replaceAll(" ", "")
							.getBytes(encoding)
							.length;
				}else {
					ByteInfo = stringContentOp.get()
							.getBytes(encoding)
							.length;
				}
				stringContentByte = String.valueOf(ByteInfo);
				request.setAttribute("stringContent", stringContent);
				request.setAttribute("encoding", encoding);
				request.setAttribute("emptyYn", emptyYn);
				request.setAttribute("stringContentByte", stringContentByte);
			} catch (UnsupportedEncodingException e) {
				request.setAttribute("stringContent", stringContent);
				request.setAttribute("encoding", encoding);
				request.setAttribute("emptyYn", emptyYn);
				request.setAttribute("stringContentByte", stringContentByte);
				log.info("exception", e);
			}
		}
	}

	@Override
	public void getInitialString(HttpServletRequest request, String stringContent) {
		String initialString = "";
		Optional<String> stringContentOp = Optional.ofNullable(stringContent);
		if(stringContentOp.isPresent()) {//전달 문자열 존재
			for(int i = 0; i < stringContentOp.get().length(); i++) {
				String stringLocationInfo = String.valueOf(stringContentOp.get().charAt(i)); //현재 위치의 한글자 추출
				if(stringLocationInfo.matches(".*[가-힣]+.*")) {// 한글일 경우
					if(stringLocationInfo.charAt(0) >= 0xAC00)
					{
						int unicode = stringLocationInfo.charAt(0) - 0xAC00;
						int index = ((unicode - (unicode % 28))/28)/21;
						initialString += INITIAL_STRING[index]; 
					}

				} else {//한글이 아닐경우
					initialString += stringLocationInfo;//변환없이 그대로 저장
				}
			}
			request.setAttribute("stringContent", stringContent);
			request.setAttribute("initialString", initialString);
		}else {
			request.setAttribute("stringContent", "잘못된 정보가 입력되었습니다.");
			request.setAttribute("initialString", initialString);
		}

	}
}

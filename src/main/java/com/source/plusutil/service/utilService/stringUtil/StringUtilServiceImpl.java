package com.source.plusutil.service.utilService.stringUtil;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class StringUtilServiceImpl implements StringUtilService{

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
}

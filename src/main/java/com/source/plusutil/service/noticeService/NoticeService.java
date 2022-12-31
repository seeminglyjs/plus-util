package com.source.plusutil.service.noticeService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface NoticeService {

	public void getNoticeList(HttpServletRequest request, Authentication authentication, Integer currentPage);
	
	public void writeNotice(String noticeTitle, String noticeContent, HttpServletRequest request, Authentication authentication);
	
	public Integer getNoticeTotalPage(Integer currentPage, Integer listSize);
	
	public void getNoticeDetailInfo(HttpServletRequest request, Integer noticeNo);
}

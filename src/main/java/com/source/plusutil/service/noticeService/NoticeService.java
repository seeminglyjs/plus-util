package com.source.plusutil.service.noticeService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface NoticeService {

	public void getNoticeList(HttpServletRequest request, Authentication authentication, Integer currentPage);
	
	public void writeNotice(String noticeTitle, String noticeContent, String category, HttpServletRequest request, Authentication authentication);
	
	public Integer getNoticeTotalPage(Integer currentPage, Integer listSize);
	
	public void getNoticeDetailInfo(HttpServletRequest request, Authentication authentication, Integer noticeNo);
	
	public void deleteNoticeInfo(HttpServletRequest request, Authentication authentication, Integer noticeNo);
	
	public void updateNoticeInfo(HttpServletRequest request, Authentication authentication, Integer noticeNo , String noticeTitle, String noticeContent);
}

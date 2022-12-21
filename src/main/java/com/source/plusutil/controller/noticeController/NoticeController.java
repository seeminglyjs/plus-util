package com.source.plusutil.controller.noticeController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.service.authService.AuthenticationService;
import com.source.plusutil.service.noticeService.NoticeService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	private final AuthenticationService authenticationService;
	
	@GetMapping("/main")
	public String noticeMain(HttpServletRequest request, Authentication authentication, Integer currentPage) {
		noticeService.getNoticeList(request, authentication, currentPage);
		return "/notice/noticeMain";
	}
	
	@GetMapping("/write")
	public String noticeWrite(HttpServletRequest request, Authentication authentication) {
		if(authenticationService.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			return "/notice/noticeWrite";
		}else {
			return "/notice/noticeMain";
		}
	}
	
	@PostMapping("/write/action")
	public String noticeWriteAction(//계시글 작성
			String noticeTitle
			, String noticeContent
			,HttpServletRequest request
			,Authentication authentication) {
		noticeService.writeNotice(noticeTitle, noticeContent, request, authentication);
		return "/notice/noticeMain";
	}
}

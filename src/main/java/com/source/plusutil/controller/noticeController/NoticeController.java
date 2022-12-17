package com.source.plusutil.controller.noticeController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/notice")
public class NoticeController {

	@GetMapping("/main")
	public String noticeMain(HttpServletRequest request) {
		return "/notice/noticeMain";
	}
	
	@GetMapping("/write")
	public String noticeWrite(HttpServletRequest request) {
		return "/notice/noticeWrite";
	}
	
	@PostMapping("/write/action")
	public String noticeWriteAction(//계시글 작성
			String noticeTitle
			, String noticeContent
			,HttpServletRequest request) {
		return "/notice/noticeMain";
	}
}

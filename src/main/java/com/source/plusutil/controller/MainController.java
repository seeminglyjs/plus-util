package com.source.plusutil.controller;


import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.plusutil.service.TestService;


@Controller
@RequiredArgsConstructor
public class MainController {

	private final TestService testService;
	
	
	@RequestMapping("/home")
	public String mainPage() {
		return "/home/homeMain";
	}
	
	/**
	 * DB 연결 확인하기
	 * 
	 * @return
	 */
	@RequestMapping("/test/select/check")
	@ResponseBody
	public String dbSelectCheck() {
		return testService.selectTestUserid().get().getInfo();
	}
}

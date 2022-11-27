package com.source.plusutil.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.plusutil.service.TestService;


@Controller
public class MainController {

	@Autowired
	TestService testService;
	
	
	@RequestMapping("/home")
	public String mainPage(HttpServletRequest request) {
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

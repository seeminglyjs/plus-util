package com.source.plusutil.controller;

import com.source.plusutil.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.plusutil.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/plus")
public class MainController {

	private final TestService testService;

	private final MainService mainService;

	@RequestMapping("/home")
	public String mainPage(HttpServletRequest request) {
		List<Map<String,String>> list = new ArrayList<>();
		mainService.callMainPageMethod(request);
		return "/home/homeMain";
	}

	@RequestMapping("/test/select/check")
	@ResponseBody
	public String dbSelectCheck() {
		return testService.selectTestUserid().get().getInfo();
	}

}

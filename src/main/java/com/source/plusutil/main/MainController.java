package com.source.plusutil.main;

import com.source.plusutil.main.MainDataDto;
import com.source.plusutil.utilEtc.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.plusutil.utilEtc.TestService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("/plus")
public class MainController {

	private final TestService testService;

	private final MainService mainService;

	@GetMapping ("/home")
	@ResponseBody
	public MainDataDto mainPage(HttpServletRequest request) {
		return mainService.callMainPageMethod(request);
	}

	@RequestMapping("/test/select/check")
	@ResponseBody
	public String dbSelectCheck() {
		return testService.selectTestUserid().get().getInfo();
	}

}

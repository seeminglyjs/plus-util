package com.source.plusutil.controller.utilController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.service.utilService.timeUtil.TimeUtilService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilController {
	
	/**
	 * 유틸메이지 메인 맵핑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/main")
	public String utilMain(HttpServletRequest request) {
		return "/util/utilMain";
	}
	
}

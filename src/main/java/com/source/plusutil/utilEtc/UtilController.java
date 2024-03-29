package com.source.plusutil.utilEtc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/plus/util")
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

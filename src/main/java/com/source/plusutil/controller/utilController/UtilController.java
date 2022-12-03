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
	
	private final TimeUtilService timeUtilService;
	
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
	
	/**
	 * 시간 관련 유틸 메인 맵핑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/time/main")
	public String timeUtilMain(HttpServletRequest request) {
		return "/util/time/timeMain";
	}
	
	@RequestMapping("/time/get/day/ofthe/week/main")
	public String requestGetDayOfTheWeek() {
		return "/util/time/getDayOfTheWeekMain";
	}
	
	/**
	 * 주어진 년 / 월 / 일 기준으로 요일을 구한다.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param request
	 * @return
	 */
	@RequestMapping("/time/get/day/ofthe/week")
	public String requestGetDayOfTheWeek(@RequestParam String year, String month, String day, HttpServletRequest request) {
		timeUtilService.getDayOfTheWeek(year, month, day, request);
		return "/util/time/getDayOfTheWeek";
	}
	
}

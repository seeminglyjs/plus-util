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
public class TimeController {
	
	private final TimeUtilService timeUtilService;
	
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
	public String requestGetDayOfTheWeekCall(@RequestParam String year, @RequestParam String month, String day, HttpServletRequest request) {
		timeUtilService.getDayOfTheWeek(year, month, day, request);
		return "/util/time/getDayOfTheWeekMain";
	}
	
	
	@RequestMapping("/time/calculate/day/main")
	public String requestCalculateDay() {
		return "/util/time/calculateDayMain";
	}
	/**
	 * 주어진 시작날짜와 끝나는 날짜 기준으로 
	 * 
	 * @param startDateStr
	 * @param EndDateStr
	 * @param request
	 * @return
	 */
	@RequestMapping("/time/calculate/day")
	public String requestCalculateDayCall(@RequestParam String startDateStr, @RequestParam String EndDateStr, HttpServletRequest request) {
		timeUtilService.calculateDate(startDateStr, EndDateStr, request);
		return "/util/time/calculateDayMain";
	}
}

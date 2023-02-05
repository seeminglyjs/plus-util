package com.source.plusutil.controller.utilController;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.service.utilService.timeUtil.TimeUtilServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/util")
@RequiredArgsConstructor
public class TimeController {
	
	private final TimeUtilServiceImpl timeUtilService;
	
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
	

	@RequestMapping("/time/get/day/of/the/week/main")
	public String requestGetDayOfTheWeek(HttpServletRequest request) {
		request.setAttribute("regexDateDefaultPermit", RegexExpressionEnum.DATE_DEFAULT_PERMIT.getRegex());
		return "/util/time/getDayOfTheWeekMain";
	}	
	/*
	 * 주어진 년 / 월 / 일 기준으로 요일을 구한다.
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/time/get/day/of/the/week/action")
	public String requestGetDayOfTheWeekCall(String dayInfo, HttpServletRequest request) {
		request.setAttribute("regexDateDefaultPermit", RegexExpressionEnum.DATE_DEFAULT_PERMIT.getRegex());
		timeUtilService.getDayOfTheWeek(dayInfo, request);
		return "/util/time/getDayOfTheWeekMain";
	}
	
	
	@RequestMapping("/time/calculate/day/main")
	public String requestCalculateDay(HttpServletRequest request) {
		request.setAttribute("regexDateDefaultPermit", RegexExpressionEnum.DATE_DEFAULT_PERMIT.getRegex());
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
	@RequestMapping("/time/calculate/day/action")
	public String requestCalculateDayCall(@RequestParam String startDateStr, @RequestParam String EndDateStr, HttpServletRequest request) {
		request.setAttribute("regexDateDefaultPermit", RegexExpressionEnum.DATE_DEFAULT_PERMIT.getRegex());
		timeUtilService.calculateDate(startDateStr, EndDateStr, request);
		return "/util/time/calculateDayMain";
	}
}

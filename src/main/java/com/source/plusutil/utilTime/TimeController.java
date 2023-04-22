package com.source.plusutil.utilTime;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plus/util")
@RequiredArgsConstructor
public class TimeController {
	
	private final TimeUtilServiceImpl timeUtilService;

	/*
	 * 주어진 년 / 월 / 일 기준으로 요일을 구한다.
	 *
	 * @param request
	 * @return
	 */
	@GetMapping("/time/day/of/the/week/")
	@ResponseBody
	public String requestGetDayOfTheWeekCall(String dayInfo, HttpServletRequest request) {
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
package com.source.plusutil.service.utilService.timeUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeUtilService {

	/**
	 * 주어진 날짜를 기준으로 그날의 요일을 구한다.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param request
	 */
	public void getDayOfTheWeek(String year, String month, String day, HttpServletRequest request) {
		Optional<String> yearOp = Optional.ofNullable(year);
		Optional<String> monthOp = Optional.ofNullable(month);
		Optional<String> dayOp = Optional.ofNullable(day);
		
		if(yearOp.isPresent() && monthOp.isPresent() && dayOp.isPresent()) {//모든 파라미터 정보가 존재한다.
			log.info("[getDayOfTheWeek request param is present] =====");
			String answer = "";
			LocalDate date = LocalDate.of(
					Integer.parseInt(yearOp.get())
					, Integer.parseInt(monthOp.get())
					, Integer.parseInt(dayOp.get())); 
			//연 월 일 순으로 적어준다.
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			if(dayOfWeek.getValue() == 1) {
				answer = "MON";
			}else if (dayOfWeek.getValue() == 2) {
				answer = "TUE";
			}else if (dayOfWeek.getValue() == 3) {
				answer = "WED";
			}else if (dayOfWeek.getValue() == 4) {
				answer = "THU";
			}else if (dayOfWeek.getValue() == 5) {
				answer = "FRI";
			}else if (dayOfWeek.getValue() == 6) {
				answer = "SAT";
			}else if (dayOfWeek.getValue() == 7) {
				answer = "SUN";
			}
			request.setAttribute("getDayOfTheWeekCheck", true);
			request.setAttribute("dayOfTheWeek", answer);
		}else {
			request.setAttribute("getDayOfTheWeekCheck", false);
		}
	}
}

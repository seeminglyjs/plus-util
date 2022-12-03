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
		
		try {
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
					answer = "MON [월요일]";
				}else if (dayOfWeek.getValue() == 2) {
					answer = "TUE [화요일]";
				}else if (dayOfWeek.getValue() == 3) {
					answer = "WED [수요일]";
				}else if (dayOfWeek.getValue() == 4) {
					answer = "THU [목요일]";
				}else if (dayOfWeek.getValue() == 5) {
					answer = "FRI [금요일]";
				}else if (dayOfWeek.getValue() == 6) {
					answer = "SAT [토요일]";
				}else if (dayOfWeek.getValue() == 7) {
					answer = "SUN [일요일]";
				}
				request.setAttribute("dayOfTheWeek", answer);
			}
		}catch (NumberFormatException e) {
			request.setAttribute("dayOfTheWeek", "적절하지 않은 값이 입력되었습니다.");
			log.info("exception", e);
		}catch (Exception e) {
			request.setAttribute("dayOfTheWeek", "예상치 못한 오류가 발생했습니다.");
			log.info("exception", e);
		}
		
	}
}

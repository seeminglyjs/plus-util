package com.source.plusutil.service.utilService.timeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeUtilServiceImpl implements TimeUtilService{

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
			return;
		}catch (Exception e) {
			request.setAttribute("dayOfTheWeek", "예상치 못한 오류가 발생했습니다.");
			log.info("exception", e);
			return;
		}
		
	}
	
	/**
	 * 전달받은 데이터 기준으로 시간차이를 계산한다.
	 * 
	 * @param startDateStr
	 * @param EndDateStr
	 * @param request
	 */
	public void calculateDate(String startDateStr, String EndDateStr, HttpServletRequest request) {
		request.setAttribute("regexDateDefaultPermit", RegexExpressionEnum.DATE_DEFAULT_PERMIT.getRegex());
		String regex = "^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$"; //날짜포멧정규식
		if(Pattern.matches(regex, startDateStr) && Pattern.matches(regex, EndDateStr) ) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date startDate = null;
			Date endDate = null;
			try {
				startDate = df.parse(startDateStr);
				endDate = df.parse(EndDateStr);
			} catch (ParseException e) {
				request.setAttribute("calculateDay", "적절하지 않은 값이 입력되었습니다.");
				log.info("exception", e);
				return;
			} catch (Exception e) {
				request.setAttribute("calculateDay", "적절하지 않은 값이 입력되었습니다.");
				log.info("exception", e);
				return;
			}
			
			//밀리 초를 -> 일자단위로 나누어준다.
			long calculateDay = ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60)) / 1000;
			log.info("calculateDay info - > " + calculateDay);
			request.setAttribute("startDateStr", startDateStr);
			request.setAttribute("endDateStr", EndDateStr);
			request.setAttribute("calculateDay", String.valueOf(calculateDay) + " 일");
		}else {
			request.setAttribute("calculateDay", "적절하지 않은 값이 입력되었습니다.");
		}
	}
	
}

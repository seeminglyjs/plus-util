package com.source.plusutil.utilTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.utilTime.dto.TimeCalculateRequestDto;
import com.source.plusutil.utilTime.dto.TimeCalculateResponseDto;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeUtilServiceImpl implements TimeUtilService {

    /*
     * 주어진 날짜를 기준으로 그날의 요일을 구한다.
     */
    public void getDayOfTheWeek(String dayInfo, HttpServletRequest request) {
        Optional<String> dayInfoOp = Optional.ofNullable(dayInfo);

        try {
            if (dayInfoOp.isPresent()) {//모든 파라미터 정보가 존재한다.
                String year = dayInfo.substring(0, 4);
                String month = dayInfo.substring(4, 6);
                String day = dayInfo.substring(6, 8);
                log.info("[getDayOfTheWeek request param is present] =====");
                String answer = "";
                LocalDate date = LocalDate.of(
                        Integer.parseInt(year)
                        , Integer.parseInt(month)
                        , Integer.parseInt(day));
                //연 월 일 순으로 적어준다.
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                if (dayOfWeek.getValue() == 1) {
                    answer = "MON [월요일]";
                } else if (dayOfWeek.getValue() == 2) {
                    answer = "TUE [화요일]";
                } else if (dayOfWeek.getValue() == 3) {
                    answer = "WED [수요일]";
                } else if (dayOfWeek.getValue() == 4) {
                    answer = "THU [목요일]";
                } else if (dayOfWeek.getValue() == 5) {
                    answer = "FRI [금요일]";
                } else if (dayOfWeek.getValue() == 6) {
                    answer = "SAT [토요일]";
                } else if (dayOfWeek.getValue() == 7) {
                    answer = "SUN [일요일]";
                }
                request.setAttribute("dayOfTheWeek", answer);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("dayOfTheWeek", "적절하지 않은 값이 입력되었습니다.");
            log.info("exception", e);
        } catch (Exception e) {
            request.setAttribute("dayOfTheWeek", "예상치 못한 오류가 발생했습니다.");
            log.info("exception", e);
        }
    }

    /*
     * 전달받은 데이터 기준으로 시간차이를 계산한다.
     *
     * @param startDateStr
     * @param EndDateStr
     * @param request
     */
    @Override
    public TimeCalculateResponseDto calculateDate(TimeCalculateRequestDto timeCalculateRequestDto) {
        String regex = "^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$"; //날짜포멧정규식
        if (timeCalculateRequestDto.fieldCheck()) {
            if (Pattern.matches(regex, timeCalculateRequestDto.getStartDateStr()) && Pattern.matches(regex, timeCalculateRequestDto.getEndDateStr())) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                Date startDate, endDate;
                try {
                    startDate = df.parse(timeCalculateRequestDto.getStartDateStr());
                    endDate = df.parse(timeCalculateRequestDto.getEndDateStr());
                } catch (Exception e) {
                    log.info("exception", e);
                    return TimeCalculateResponseDto.builder().calculateDay("날짜 계산 중 오류가 발생했습니다.").build();
                }
                //밀리 초를 -> 일자단위로 나누어준다.
                long calculateDay = ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60)) / 1000;
                log.info("calculateDay info - > " + calculateDay);
				return TimeCalculateResponseDto.builder().calculateDay(calculateDay +" 일").build();
            } else {
                return TimeCalculateResponseDto.builder().calculateDay("적절하지 않은 값이 입력되었습니다.").build();
            }
        } else {
            return TimeCalculateResponseDto.builder().calculateDay("요청정보가 혹시 비어있는지 확인해 주세요.").build();
        }
    }
}
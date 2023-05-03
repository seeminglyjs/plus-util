package com.source.plusutil.utilTime;

import com.source.plusutil.utilTime.dto.TimeCalculateRequestDto;
import com.source.plusutil.utilTime.dto.TimeCalculateResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface TimeUtilService {
    void getDayOfTheWeek(String dayInfo, HttpServletRequest request);
    TimeCalculateResponseDto calculateDate(TimeCalculateRequestDto timeCalculateRequestDto);
}

package com.source.plusutil.service.utilService.timeUtil;

import javax.servlet.http.HttpServletRequest;

public interface TimeUtilService {
    public void getDayOfTheWeek(String dayInfo, HttpServletRequest request);
    public void calculateDate(String startDateStr, String EndDateStr, HttpServletRequest request);
}

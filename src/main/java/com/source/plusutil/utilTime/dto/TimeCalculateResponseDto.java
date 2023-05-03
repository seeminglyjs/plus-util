package com.source.plusutil.utilTime.dto;

import lombok.Builder;

@Builder
public class TimeCalculateResponseDto {
    String calculateDay;

    public String getCalculateDay() {
        return calculateDay;
    }

    public void setCalculateDay(String calculateDay) {
        this.calculateDay = calculateDay;
    }

    @Override
    public String toString() {
        return "TimeCalculateResponseDto{" +
                "calculateDay='" + calculateDay + '\'' +
                '}';
    }
}

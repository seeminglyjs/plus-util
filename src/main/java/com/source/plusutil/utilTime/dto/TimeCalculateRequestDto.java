package com.source.plusutil.utilTime.dto;

public class TimeCalculateRequestDto {
    String startDateStr;
    String endDateStr;

    public String getStartDateStr() {
        if(startDateStr!=null)return startDateStr;
        else return "empty";
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        if(endDateStr !=null)return endDateStr;
        else return "empty";
    }

    public void setEndDateStr(String endDateStr) {
        endDateStr = endDateStr;
    }

    @Override
    public String toString() {
        return "TimeCalculateRequestDto{" +
                "startDateStr='" + startDateStr + '\'' +
                ", endDateStr='" + endDateStr + '\'' +
                '}';
    }

    //요청준 정보가 혹시 비어있는지를 체크한다.
    public boolean fieldCheck(){
        return !getStartDateStr().equals("empty") && !getEndDateStr().equals("empty");
    }
}

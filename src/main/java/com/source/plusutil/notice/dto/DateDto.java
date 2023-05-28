package com.source.plusutil.notice.dto;


import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateDto { //시간 포맷을 정리할 객체
	
	private String day;
	private String time;

	@Override
	public String toString() {
		return "DateDto{" +
				"day='" + day + '\'' +
				", time='" + time + '\'' +
				'}';
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}

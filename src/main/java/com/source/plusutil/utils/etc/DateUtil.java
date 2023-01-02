package com.source.plusutil.utils.etc;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

	public static String getDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String dateStr = sdf.format(date);
		log.info("writeNotice TIME -> " + dateStr);
		return dateStr;
	}
	
	public static String getDateStrCommaLen14(String dateStr) {//날짜 형식 문자열 추출 2022.12.12 길이 14이상
		return dateStr.substring(0,4) + "." + dateStr.substring(4,6) + "." + dateStr.substring(6,8);
	}
	
	public static String getTimeStrCommaLen14(String dateStr) {//시간 형식 추출 12:01:30 길이 14이상
		return dateStr.substring(8,10) + ":" + dateStr.substring(10,12) + ":" + dateStr.substring(12,14);
	}
}

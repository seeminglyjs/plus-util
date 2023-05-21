package com.source.plusutil.utils.etc;

import java.text.SimpleDateFormat;
import java.util.Date;


public final class DateUtil {

	public static String getDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		return sdf.format(date);
	}

	public static String getDateStryyyyMMdd(){//현재 날짜 기준 yyyyMMdd 추출
		return getDateString().substring(0,8);
	}

	public static String getDateStryyyyMMdd(String dateStr){//날짜 형식 문자열 기준 yyyyMMdd 추출
		return dateStr.substring(0,8);
	}

	public static String getDateStrCommaLen14(String dateStr) {//날짜 형식 문자열 추출 2022.12.12 길이 14이상
		return dateStr.substring(0,4) + "." + dateStr.substring(4,6) + "." + dateStr.substring(6,8);
	}
	
	public static String getTimeStrCommaLen14(String dateStr) {//시간 형식 추출 12:01:30 길이 14이상
		return dateStr.substring(8,10) + ":" + dateStr.substring(10,12) + ":" + dateStr.substring(12,14);
	}
}

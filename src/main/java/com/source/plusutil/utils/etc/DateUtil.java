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
}

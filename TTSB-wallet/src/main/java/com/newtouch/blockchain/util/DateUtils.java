package com.newtouch.blockchain.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import cn.kklazy.utils.StringUtils;

public class DateUtils {

	/**
	 * 
	* @Title: strParse  
	* @Description: TODO 
	* @param @param time ("2018-09-08")
	* @param @return    参数  
	* @return LocalDateTime    返回类型  
	* @throws
	 */
	public static LocalDateTime strParse(String time) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			    .appendPattern("yyyy-MM-dd[['T'hh][:mm][:ss]]")
			    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			    .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
			    .toFormatter();
		return LocalDateTime.parse(time, formatter);
	}
	/**
	 * String转LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate strParseLocalDate(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	/**
	 * LocalDateTime转String
	 * @param localDateTime
	 * @return
	 */
	public static String format(LocalDateTime localDateTime) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return localDateTime.format(df);
	}
	
	/**
	 * LocalDate转String
	 * @param localDateTime
	 * @return
	 */
	public static String format(LocalDate localDate) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(df);
	}
}

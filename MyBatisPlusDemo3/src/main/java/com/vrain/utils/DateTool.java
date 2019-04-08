package com.vrain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 * 
 * @author zhu
 *
 */
public class DateTool {
	/**
	 * 调用该方法，返回日期格式是：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getData() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		return date;
	}

	/**
	 * 调用该方法，需输入自定义的日期输出格式
	 * 
	 * @param pattern
	 *            自定义的日期输出格式
	 * @return
	 */
	public static String getData(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String date = format.format(new Date());
		return date;
	}

	/**
	 * 返回当前当前时间的毫秒值
	 * 
	 * @return
	 */
	public static String getTime() {
		Long time = new Date().getTime();
		return time.toString();
	}

}

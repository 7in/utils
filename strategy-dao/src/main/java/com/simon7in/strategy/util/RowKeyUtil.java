package com.simon7in.strategy.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author wteng.hmg
 */
public class RowKeyUtil {

	private static DecimalFormat dateFormat = new DecimalFormat("00000000000000");// 14个0

	private static DecimalFormat randomFormat = new DecimalFormat("0000");

	private static Random random = new Random();

	private static Long maxDateTime;

	static {
		Calendar calendar = Calendar.getInstance();
		calendar.set(3000, 1, 1, 0, 0, 0);// 3000-1-1 00:00:00.0
		calendar.set(Calendar.MILLISECOND, 0);
		Date future = calendar.getTime();
		maxDateTime = future.getTime();
	}

	/**
	 * 随机4位数
	 * 
	 * @return
	 */
	public static String getRandom() {
		int i = random.nextInt(9999);
		return randomFormat.format(i);
	}

	/**
	 * 3000年1月1日到date的距离，为实现时间倒序
	 * 
	 * @param date
	 * @return
	 */
	public static String dateTranf(Date date) {
		if (date == null) {
			date = new Date();
		}
		long length = maxDateTime - date.getTime();
		return dateFormat.format(length).toString();
	}

	/**
	 * 获得最大的时间
	 * 
	 * @return
	 */
	public static String getMaxDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 1, 1, 0, 0, 0);// 3000-1-1 00:00:00.0
		calendar.set(Calendar.MILLISECOND, 0);
		Date future = calendar.getTime();
		return dateTranf(future);
	}

	/**
	 * 获得最小的时间
	 * 
	 * @return
	 */
	public static String getMinDate() {
		return dateFormat.format(1000);
	}
}

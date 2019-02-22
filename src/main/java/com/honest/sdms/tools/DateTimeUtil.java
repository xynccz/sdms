package com.honest.sdms.tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期处理工具类 "yyyy-mm-dd","yy-mm-dd","hh:mi", "hh24:mi:ss",
 * "yyyy/mm/dd hh24_mi_ss", "mi_ss_hh dd_yy_mm"
 */
public class DateTimeUtil {
	private Calendar calendar = Calendar.getInstance();
	public static final int FLD_YEAR = Calendar.YEAR;
	public static final int FLD_MONTH = Calendar.MONTH;
	public static final int FLD_DAY = Calendar.DAY_OF_MONTH;
	public static final int FLD_HOUR = Calendar.HOUR_OF_DAY;
	public static final int FLD_MINUTE = Calendar.MINUTE;
	public static final int FLD_SECOND = Calendar.SECOND;
	public static final int FLD_MILLISECOND = Calendar.MILLISECOND;
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";

	public static Date strToDate(String str, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date date = null;
		try {
			if (str != null && !"".equals(str)) {
				date = sf.parse(str);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public DateTimeUtil() {
	}

	public DateTimeUtil(long time) {
		if (time > 0) {
			this.setTime(time);
		}
	}

	public DateTimeUtil(int year, int month, int day, int hour, int minute,int second, int millis) {
		calendar.set(year, month - 1, day, hour, minute, second);
		calendar.set(Calendar.MILLISECOND, millis);
	}

	public DateTimeUtil(int year, int month, int day) {
		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public DateTimeUtil(DateTimeUtil t) {
		if (t != null) {
			calendar.setTimeInMillis(t.calendar.getTimeInMillis());
		}
	}

	public DateTimeUtil(Date d) {
		if (d != null) {
			calendar.setTimeInMillis(d.getTime());
		}
	}

	public DateTimeUtil(Calendar c) {
		if (c != null) {
			this.setTime(c.getTimeInMillis());
		}
	}

	/**
	 * get a clone
	 * 
	 * @return new DateTimeUtil object
	 */
	public DateTimeUtil copy() {
		return new DateTimeUtil(this);
	}

	/**
	 * 创建指定格式的DateTimeUtil对象
	 * 
	 * @param src 日期字符串
	 * @param format 日期格式 ，比如：yyyy-MM-dd
	 * @return DateTimeUtil if successfully parsed and null if failed
	 */
	public static DateTimeUtil create(String src, String format) {
		DateTimeUtil date = new DateTimeUtil();
		date.parse2(src, format);
		return date;
	}
	
	public static String getLastUpdateDate(){
		return new DateTimeUtil().toString(DATE_FORMAT);
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public void setYear(int year) {
		calendar.set(Calendar.YEAR, year);
	}

	public int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	public void setMonth(int month) {
		calendar.set(Calendar.MONTH, month - 1);
	}

	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public void setDay(int day) {
		calendar.set(Calendar.DAY_OF_MONTH, day);
	}

	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	public void setMinute(int minute) {
		calendar.set(Calendar.MINUTE, minute);
	}

	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public void setHour(int hour) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
	}

	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}

	public void setSecond(int second) {
		calendar.set(Calendar.SECOND, second);
	}

	public int getMillis() {
		return calendar.get(Calendar.MILLISECOND);
	}

	public void setMillis(int ms) {
		calendar.set(Calendar.MILLISECOND, ms);
	}

	public void setTime(long time) {
		calendar.setTimeInMillis(time);
	}

	public void setTime(Date d) {
		this.setTime(d.getTime());
	}

	public long getTime() {
		return calendar.getTimeInMillis();
	}

	public void setTime(Calendar c) {
		calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
	}

	public Date toDate() {
		return calendar.getTime();
	}

	public Calendar toCalendar() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(calendar.getTimeInMillis());
		return c;
	}

	public java.sql.Timestamp toTimestamp() {
		return new java.sql.Timestamp(calendar.getTimeInMillis());
	}

	public int getWeekOfYear() {
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * calculator
	 * 
	 * @param field
	 *            FLD_YEAR || FLD_MONTH || FLD_DAY || FLD_HOUR || FLD_MINUTE ||
	 *            FLD_SECOND || FLD_MILLISECOND
	 * @param amount
	 *            positive(+) for adding and negative(-) for subtraction
	 */
	public void add(int field, int amount) {
		switch (field) {
		case FLD_YEAR:
			calendar.add(Calendar.YEAR, amount);
			break;
		case FLD_MONTH:
			calendar.add(Calendar.MONTH, amount);
			break;
		case FLD_DAY:
			calendar.add(Calendar.DAY_OF_MONTH, amount);
			break;
		case FLD_HOUR:
			calendar.add(Calendar.HOUR_OF_DAY, amount);
			break;
		case FLD_MINUTE:
			calendar.add(Calendar.MINUTE, amount);
			break;
		case FLD_SECOND:
			calendar.add(Calendar.SECOND, amount);
			break;
		case FLD_MILLISECOND:
			calendar.add(Calendar.MILLISECOND, amount);
			break;
		}
	}

	/**
	 * comparator
	 * 
	 * @param s DateTimeUtil object
	 * @return true if this DateTimeUtil's time is after the given DateTimeUtils and false if not
	 */
	public boolean after(DateTimeUtil s) {
		return calendar.getTimeInMillis() > s.calendar.getTimeInMillis();
	}

	/**
	 * comparator
	 * 
	 * @param o DateTimeUtil object
	 * @return true if this DateTimeUtil's time equals the given DateTimeUtils and false if not
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof DateTimeUtil)) {
			return false;
		}
		DateTimeUtil s = (DateTimeUtil) o;
		return calendar.getTimeInMillis() == s.calendar.getTimeInMillis();
	}

	/**
	 * comparator
	 * 
	 * @param s DateTimeUtil object
	 * @return true if this DateTimeUtil's time is before the given DateTimeUtils and false if not
	 */
	public boolean before(DateTimeUtil s) {
		return calendar.getTimeInMillis() < s.calendar.getTimeInMillis();
	}

	/**
	 * test whether current DateTimeUtil's year is a peak year<br>
	 * a peak year has 366 days
	 * @return true if this DateTimeUtil's year is a peak year and false if not
	 */
	public boolean isPeakYear() {
		int year = calendar.get(Calendar.YEAR);
		return (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0));
	}

	/**
	 * get a "yyyy-mm-dd" String
	 * @return a String presents current DateTimeUtil
	 */
	public String yyyymmdd() {
		int month = calendar.get(Calendar.MONTH) + 1, day = calendar
				.get(Calendar.DAY_OF_MONTH);
		return calendar.get(Calendar.YEAR) + "-"
				+ (month < 10 ? "0" + month : String.valueOf(month)) + "-"
				+ (day < 10 ? "0" + day : String.valueOf(day));
	}

	/**
	 * get a "yyyymm" String
	 * @return a String presents current DateTimeUtil
	 */
	public String yyyymm() {
		int month = calendar.get(Calendar.MONTH) + 1;
		return calendar.get(Calendar.YEAR) + (month < 10 ? "0" + month : String.valueOf(month));
	}

	/**
	 * get a "weekyy" String
	 * 
	 * @return a String presents current DateTimeUtil
	 * 
	 *         public String weekyy() { String yy =
	 *         String.valueOf(calendar.get(Calendar.YEAR)).substring(2,4);
	 *         String weak = (calendar.get(Calendar.WEEK_OF_YEAR) < 10?"0" +
	 *         calendar
	 *         .get(Calendar.WEEK_OF_YEAR):String.valueOf(calendar.get(Calendar
	 *         .WEEK_OF_YEAR))) ; return weak + yy; }
	 */
	/**
	 * get a "yyweek" String
	 * 
	 * @return a String presents current DateTimeUtil
	 * 
	 *         public String yyweek() { String yy =
	 *         String.valueOf(calendar.get(Calendar.YEAR)).substring(2,4);
	 *         String weak = (calendar.get(Calendar.WEEK_OF_YEAR) < 10?"0" +
	 *         calendar
	 *         .get(Calendar.WEEK_OF_YEAR):String.valueOf(calendar.get(Calendar
	 *         .WEEK_OF_YEAR))) ; return yy + weak; }
	 */
	/**
	 * get a "weekyy" String
	 * 
	 * @return a String presents current DateTimeUtil
	 */
	public String weekyy() {

		int w = calendar.get(Calendar.WEEK_OF_YEAR);
		int year = calendar.get(Calendar.YEAR);

		if (w == 1) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK));
			int ny = c.get(Calendar.YEAR);
			if (year != ny) {
				year = ny;
			}
		}
		String yy = String.valueOf(year).substring(2, 4);
		String weak = (calendar.get(Calendar.WEEK_OF_YEAR) < 10 ? "0"
				+ calendar.get(Calendar.WEEK_OF_YEAR) : String.valueOf(calendar
				.get(Calendar.WEEK_OF_YEAR)));
		return weak + yy;
	}

	/**
	 * get a "yyweek" String
	 * 
	 * @return a String presents current DateTimeUtil
	 */
	public String yyweek() {
		int w = calendar.get(Calendar.WEEK_OF_YEAR);
		int year = calendar.get(Calendar.YEAR);

		if (w == 1) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK));
			int ny = c.get(Calendar.YEAR);
			if (year != ny) {
				year = ny;
			}
		}
		String yy = String.valueOf(year).substring(2, 4);
		String weak = (calendar.get(Calendar.WEEK_OF_YEAR) < 10 ? "0"
				+ calendar.get(Calendar.WEEK_OF_YEAR) : String.valueOf(calendar
				.get(Calendar.WEEK_OF_YEAR)));
		return yy + weak;
	}

	/**
	 * get a "yyyy-mm-dd" String
	 * 
	 * @return a String presents current DateTimeUtil
	 */
	public String yyyymmddhhmi() {
		int month = calendar.get(Calendar.MONTH) + 1, day = calendar
				.get(Calendar.DAY_OF_MONTH), hour = calendar
				.get(Calendar.HOUR_OF_DAY), minute = calendar
				.get(Calendar.MINUTE);
		return calendar.get(Calendar.YEAR) + "-"
				+ (month < 10 ? "0" + month : String.valueOf(month)) + "-"
				+ (day < 10 ? "0" + day : String.valueOf(day)) + " "
				+ (hour < 10 ? "0" + hour : String.valueOf(hour)) + ":"
				+ (minute < 10 ? "0" + minute : String.valueOf(minute));
	}

	/**
	 * get a String presents current DateTimeUtil
	 * 
	 * @param formatString
	 *            a String points out the format<br>
	 *            formatString can contain any of "yyyy", "yy", "mm", "dd",
	 *            "hh24", "hh", "mi", "ss" for any times
	 * @return a String presents current DateTimeUtil
	 */
	public String toString(String formatString) {
		String format = formatString.toLowerCase();
		String[] accept = { "yyyy", "yy", "mm", "dd", "hh24", "hh", "mi", "ss",
				"ms" };
		int year = calendar.get(Calendar.YEAR), month = calendar
				.get(Calendar.MONTH) + 1, day = calendar
				.get(Calendar.DAY_OF_MONTH), hour = calendar
				.get(Calendar.HOUR_OF_DAY), minute = calendar
				.get(Calendar.MINUTE), second = calendar.get(Calendar.SECOND), millis = calendar
				.get(Calendar.MILLISECOND);
		String[] data = {
				String.valueOf(year), // yyyy
				year > 99 ? String.valueOf(year).substring(2) : String
						.valueOf(year),// yy
				month > 9 ? String.valueOf(month) : "0" + month, // mm
				day > 9 ? String.valueOf(day) : "0" + day, // dd
				hour > 9 ? String.valueOf(hour) : "0" + hour, // hh24
				(hour % 24) > 9 ? String.valueOf((hour % 24)) : "0"
						+ (hour % 24), // hh
				minute > 9 ? String.valueOf(minute) : "0" + minute, // mi
				second > 9 ? String.valueOf(second) : "0" + second, // ss
				millis > 99 ? String.valueOf(second) : (millis > 9 ? "0"
						+ millis : "00" + millis) // ms

		};
		for (int i = 0, len = accept.length; i < len; i++) {
			format = DateTimeUtil.replace(format, accept[i], data[i]);
		}
		return format;
	}

	/**
	 * replace s1 with s2 in source String
	 * @param source in which to find s1
	 * @param s1 the String to be replaced
	 * @param s2 the String used to replace
	 * @return the replaced String
	 */
	public static String replace(String source, String s1, String s2) {
		StringBuffer buf = new StringBuffer(source.length() * 2);
		int a = 0, b = source.indexOf(s1);
		char[] chars = source.toCharArray();
		while (b >= 0) {
			buf.append(chars, a, b - a).append(s2);
			a = b + s1.length();
			b = source.indexOf(s1, a);
		}
		if (a < source.length()) {
			buf.append(chars, a, source.length() - a);
		}

		return buf.toString();
	}

	/**
	 * get the day of the week, 1 for mon, 2 for tue, 3 for wed...7 for sunday.
	 */
	public int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * for debug only
	 */
	public void show() {
		System.out.println("\r\nyear==" + calendar.get(Calendar.YEAR)
				+ " month==" + (calendar.get(Calendar.MONTH) + 1) + " day=="
				+ calendar.get(Calendar.DAY_OF_MONTH) + " hour=="
				+ calendar.get(Calendar.HOUR_OF_DAY) + " minute=="
				+ calendar.get(Calendar.MINUTE) + " second=="
				+ calendar.get(Calendar.SECOND) + " millis=="
				+ calendar.get(Calendar.MILLISECOND) + " yyyy-mm-dd=="
				+ yyyymmdd() + " yyyy-mm-dd hh24:mi:ss:ms=="
				+ toString("yyyy-mm-dd hh24:mi:ss:ms") + " day_of_week=="
				+ getDayOfWeek());
	}

	public void parse(String src, String format) {
		int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0, millis = 0;
		try {
			String regEx = "";
			regEx = "^(\\d{2,4})(-|/)(\\d{1,2})(-|/)(\\d{1,2})(\\s{0,10})(\\d{0,2})(:{0,1})(\\d{0,2})(:{0,1})(\\d{0,2})$";

			Pattern p = Pattern.compile(regEx);

			Matcher m = p.matcher(src);

			if (m.find()) {
				MatchResult r = m.toMatchResult();
				if (m.groupCount() >= 1) {
					year = Integer.valueOf(r.group(1));
				}
				if (m.groupCount() >= 3) {
					month = Integer.valueOf(r.group(3));
				}
				if (m.groupCount() >= 5) {
					day = Integer.valueOf(r.group(5));
				}
				if (r.group(7).length() > 0 && m.groupCount() >= 7) {
					hour = Integer.valueOf(r.group(7));
				}
				if (r.group(9).length() > 0 && m.groupCount() >= 9) {
					minute = Integer.valueOf(r.group(9));
				}
				if (r.group(11).length() > 0 && m.groupCount() >= 11) {
					second = Integer.valueOf(r.group(11));
				}

				this.setYear(year);
				this.setMonth(month);
				this.setDay(day);
				this.setHour(hour);
				this.setMinute(minute);
				this.setSecond(second);
				this.setMillis(millis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parse2(String src, String format) {
		int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0, millis = 0, span = 0;
		try {
			format = format.toLowerCase();
			int max = src.length() - 1;

			// year
			int i = format.indexOf("yyyy");
			if (i >= 0 && i <= (max - 3)) {
				year = Character.digit(src.charAt(i), 10) * 1000
						+ Character.digit(src.charAt(i + 1), 10) * 100
						+ Character.digit(src.charAt(i + 2), 10) * 10
						+ Character.digit(src.charAt(i + 3), 10);
			} else {
				i = format.indexOf("yy");
				if (i >= 0 && i <= (max - 1)) {
					year = Character.digit(src.charAt(i), 10) * 10
							+ Character.digit(src.charAt(i + 1), 10);
					if (year < 50) {
						year += 2000;
					} else {
						year += 1900;
					}
				}
			}

			// month
			i = format.indexOf("mm");
			if (i >= 0 && i <= max) {
				if (i == max || Character.digit(src.charAt(i + 1), 10) == -1) {
					span += 1;
					month = Character.digit(src.charAt(i), 10);
				} else {
					month = Character.digit(src.charAt(i), 10) * 10
							+ Character.digit(src.charAt(i + 1), 10);
				}
			}

			// day
			i = format.indexOf("dd") - span;
			if (i >= 0 && i <= max) {
				if (i == max || Character.digit(src.charAt(i + 1), 10) == -1) {
					span += 1;
					day = Character.digit(src.charAt(i), 10);
				} else {
					day = Character.digit(src.charAt(i), 10) * 10
							+ Character.digit(src.charAt(i + 1), 10);
				}
			}

			// hour
			i = format.indexOf("hh") - span;
			if (i >= 0 && i <= max) {
				hour = Character.digit(src.charAt(i), 10) * 10
						+ Character.digit(src.charAt(i + 1), 10);
			}

			// minute
			i = format.indexOf("mi") - span;
			if (i >= 0 && i <= max) {
				minute = Character.digit(src.charAt(i), 10) * 10
						+ Character.digit(src.charAt(i + 1), 10);
			}

			// second
			i = format.indexOf("ss") - span;
			if (i >= 0 && i <= max) {
				second = Character.digit(src.charAt(i), 10) * 10
						+ Character.digit(src.charAt(i + 1), 10);
			}

			// millis
			i = format.indexOf("fff") - span;
			if (i >= 0 && i <= (max - 1)) {
				millis = Character.digit(src.charAt(i), 10) * 100
						+ Character.digit(src.charAt(i + 1), 10) * 10
						+ Character.digit(src.charAt(i + 2), 10);
			}

			this.setYear(year);
			this.setMonth(month);
			this.setDay(day);
			this.setHour(hour);
			this.setMinute(minute);
			this.setSecond(second);
			this.setMillis(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param t
	 * @return
	 */
	public static Timestamp getDayEarliest(Timestamp t) {
		DateTimeUtil dt = new DateTimeUtil(t);
		dt.setHour(0);
		dt.setMinute(0);
		dt.setSecond(0);
		dt.setMillis(0);
		return dt.toTimestamp();
	}

	/**
	 * @param t
	 * @return
	 */
	public static Date getDayEarliest(Date t) {
		DateTimeUtil dt = new DateTimeUtil(t);
		dt.setHour(0);
		dt.setMinute(0);
		dt.setSecond(0);
		dt.setMillis(0);
		return dt.toDate();
	}

	/**
	 * @param t
	 * @return
	 */
	public static Timestamp getDayLatest(Timestamp t) {
		DateTimeUtil dt = new DateTimeUtil(t);
		dt.setHour(23);
		dt.setMinute(59);
		dt.setSecond(59);
		dt.setMillis(999);
		return dt.toTimestamp();
	}

	/**
	 * @param t
	 * @return
	 */
	public static Date getDayLatest(Date t) {
		DateTimeUtil dt = new DateTimeUtil(t);
		dt.setHour(23);
		dt.setMinute(59);
		dt.setSecond(59);
		dt.setMillis(999);
		return dt.toDate();
	}

	public static DateTimeUtil getMondayCurrent() {
		DateTimeUtil dt = new DateTimeUtil();
		if (dt.getDayOfWeek() == 1) {
			dt.add(DateTimeUtil.FLD_DAY, -6);
		}
		while (dt.getDayOfWeek() > 2) {
			dt.add(DateTimeUtil.FLD_DAY, -1);
		}
		return dt;
	}

	public int getWeekOfMonth() {
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		DateTimeUtil new_date = new DateTimeUtil();
		int year1 = new_date.getYear();
		int month1 = new_date.getMonth();
		int month_1 = DateTimeUtil.getMondayCurrent().getMonth();
		DateTimeUtil date1 = new DateTimeUtil(year1, month1, 1);
		int week1 = date1.getDayOfWeek();
		DateTimeUtil date2;
		int year2;
		int month2;
		if (month1 == 1) {
			year2 = year1 - 1;
			month2 = 12;
		} else {
			year2 = year1;
			month2 = month1 - 1;
		}
		date2 = new DateTimeUtil(year2, month2, 1);
		int week2 = date2.getDayOfWeek();
		if (week1 == 1 || week1 == 2)
			return week;
		else if ((week1 != 1 || week1 != 2) && (week2 == 1 || week2 == 2)
				&& (month_1 != month1)) {
			return week;
		} else {
			if (week != 1)
				week = week - 1;
			return week;
		}
	}

	public static DateTimeUtil getMondayCurrent(int year, int month, int day) {
		DateTimeUtil dt = new DateTimeUtil(year, month, day);
		if (dt.getDayOfWeek() == 1) {
			dt.add(DateTimeUtil.FLD_DAY, -6);
		}
		while (dt.getDayOfWeek() > 2) {
			dt.add(DateTimeUtil.FLD_DAY, -1);
		}
		return dt;
	}

	public int getWeekOfMonth(DateTimeUtil new_date) {
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		int year1 = new_date.getYear();
		int month1 = new_date.getMonth();
		int month_1 = DateTimeUtil.getMondayCurrent(new_date.getYear(),new_date.getMonth(), new_date.getDay()).getMonth();
		DateTimeUtil date1 = new DateTimeUtil(year1, month1, 1);
		int week1 = date1.getDayOfWeek();

		DateTimeUtil date2;
		int year2;
		int month2;
		if (month1 == 1) {
			year2 = year1 - 1;
			month2 = 12;
		} else {
			year2 = year1;
			month2 = month1 - 1;
		}
		date2 = new DateTimeUtil(year2, month2, 1);
		int week2 = date2.getDayOfWeek();

		if (week1 == 1 || week1 == 2)
			return week;
		else if ((week1 != 1 || week1 != 2) && (week2 == 1 || week2 == 2)
				&& (month_1 != month1)) {
			return week;
		} else {
			if (week != 1)
				week = week - 1;
			return week;
		}
	}


	/**
	 * 获取指定月最后一天的日期
	 */
	public static DateTimeUtil getMonthOfLastDay(int year, int month) {
		DateTimeUtil date = new DateTimeUtil(year, month, 28);
		boolean isover = true;
		while (isover) {
			date.add(DateTimeUtil.FLD_DAY, 1);
			if (date.getMonth() != month) {
				isover = false;
				date.add(DateTimeUtil.FLD_DAY, -1);
			}
		}
		return date;
	}
}

/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 【类说明】这是一个关于日期操作的类,包含了一些常用日期操作方法
 * @author yehongyu.org
 * @version 1.0 2007-12-3 下午01:30:06
 */
public class DateUtils {
	
    private static DateUtils instance = new DateUtils();
    private static final String DEF_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_1 = "yyyy/MM/dd";
    private static final String DATE_FORMAT_2 = "yyyyMMdd";
    private static final String DATE_FORMAT_3 = "MM/dd/yy";
    private static final String DATE_FORMAT_4 = "dd.MM.yy";
    private static final String DATE_FORMAT_5 = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_6 = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT_7 = "yyyy:MM:dd HH:mm:ss";
    private static final String DATE_FORMAT_8 = "yyyy:MM:dd HH:mm";
    private static final String DATE_FORMAT_9 = "yyyyMMdd HHmmss";
    private static final String DATE_FORMAT_10 = "yyyyMMdd HHmm";
    private static final String DATE_FORMAT_11 = "dd.MM.yy HH:mm";
    private static final String DATE_FORMAT_12 = "MM/dd/yy hh:mm a";
    private static final String DATE_FORMAT_13 = "yyyy:MM:dd HH:mm:sss";
    private static final String DATE_FORMAT_14 = "HH:mm:ss dd.MM.yyyy";
//	private static final String DEF_TIME_FORMAT = "HH:mm:ss";
	
    /**
     * 这是一个单例模式的设计方式,在这个法中主要是得到本类的实例方法
     */
    public static DateUtils getInstance() {
        return instance;
    }
    /** 
     * 私有构造方法，防止外部创建本类实例 
     */
    private DateUtils(){}
    
    /**
     * 【函数功能】判断输入的字符串是否为日期格式yyyy-MM-dd
     * @param datestr
     * @return true or false
     */
    public static boolean isDate(String datestr) {
		DateFormat parser = new SimpleDateFormat(DEF_DATE_FORMAT);
		try {
			parser.parse(datestr);
		} catch (ParseException e) {
			// e.printStackTrace();
			return false; // 如果不能解析则不是日期字符串
		}
		return true;
	}

    /**
     * 【函数功能】根据日期对象获取日期字串
     * @param date 日期对象
     * @return String 日期字符串,默认格式yyyy-MM-dd
     */
    public static String getDateString(Date date) {
    	if(date==null) return "";
        return getDateString(date,DEF_DATE_FORMAT);
    }

    /**
     * 【函数功能】根据日期对象按指定格式获取日期字串
     * @param date 日期对象
     * @param pattern 指定格式
     * @return String 日期字符串（指定格式）
     */
    public static String getDateString(Date date,String pattern) {
        DateFormat parser = new SimpleDateFormat(pattern);
        return parser.format(date);
    }

    /**
     * 【函数功能】将日期字符串按指定格式转换为日期对象
     * @param datastr 日期字符串
     * @param pattern 格式字串
     * @return Date对象
     */
    public static Date parseDate(String datastr, String pattern) {
        try {
            DateFormat parser = new SimpleDateFormat(pattern);
            return parser.parse(datastr);
        } catch (ParseException ex) {
        	return null;
        }
    }
    
    /**
     * 【函数功能】将字符串转换为日期
     * @param datestr String 日期字符串
     * @return Date 转换失败返回Null
     */
    public static Date parseDate(String datestr) {
		if (datestr == null||datestr.trim().equals("")) return null;
		String[] patterns = new String[15];
		patterns[0] = DEF_DATE_FORMAT;
		patterns[1] = DATE_FORMAT_1;
		patterns[2] = DATE_FORMAT_2;
		patterns[3] = DATE_FORMAT_3;
		patterns[4] = DATE_FORMAT_4;
		patterns[5] = DATE_FORMAT_5;
		patterns[6] = DATE_FORMAT_6;
		patterns[7] = DATE_FORMAT_7;
		patterns[8] = DATE_FORMAT_8;
		patterns[9] = DATE_FORMAT_9;
		patterns[10] = DATE_FORMAT_10;
		patterns[11] = DATE_FORMAT_11;
		patterns[12] = DATE_FORMAT_12;
		patterns[13] = DATE_FORMAT_13;
		patterns[14] = DATE_FORMAT_14;
		for (int i = 0; i < patterns.length; i++) {
			try {
				DateFormat parser = new SimpleDateFormat(patterns[i]);
				return parser.parse(datestr);
			} catch (ParseException ex) {
			}
		}
		
		return null;
	}
    
    /**
     * 【函数功能】获得指定日期所在week的起始日期
     * @param strDate String 指定的日期格式为yyyy-MM-dd
     * @return String 返回的日期格式为yyyy-MM-dd
     */
    public static String getCurWeekBegin(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.parse(strDate, new ParsePosition(0));
        Calendar calendar = dateFormat.getCalendar();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, 1 - day);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 【函数功能】获得指定日期所在week的终止日期
     * @param strDate String 指定的日期格式为yyyy-MM-dd
     * @return String 返回的日期格式为yyyy-MM-dd
     */
    public static String getCurWeekEnd(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.parse(strDate, new ParsePosition(0));
        Calendar calendar = dateFormat.getCalendar();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, 7 - day);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 【函数功能】Timestamp型日期的加减
     * @param datestr Timestamp 传入的基础时间
     * @param type String 加减的类型: D 日期 M 月份 Y 年 HH 小时 MI 分钟 SS 秒
     * @param into int 加减的数量
     * @return Timestamp 返回时间
     */
    public static Timestamp addDate(Timestamp date, String type, int into) {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime(new java.util.Date(date.getTime()));
        if (type.equals("D")) {
            grc.add(GregorianCalendar.DATE, into);
        } else if (type.equals("M")) {
            grc.add(GregorianCalendar.MONTH, into);
        } else if (type.equals("Y")) {
            grc.add(GregorianCalendar.YEAR, into);
        } else if (type.equals("HH")) {
            grc.add(GregorianCalendar.HOUR, into);
        } else if (type.equals("MI")) {
            grc.add(GregorianCalendar.MINUTE, into);
        } else if (type.equals("SS")) {
            grc.add(GregorianCalendar.SECOND, into);
        }
        return new Timestamp(grc.getTimeInMillis());
    }

    /**
     * 【函数功能】String型日期的加减
     * @param datestr String 传入的基础时间
     * @param type String 加减的类型: D 日期 M 月份 Y 年 HH 小时 MI 分钟 SS 秒
     * @param into int 加减的数量
     * @return String 返回时间 yyyy-MM-dd HH:mm:ss
     */
    public static String addDate(String datestr, String type, int into) {
		Timestamp ts = addDate(new Timestamp(parseDate(datestr).getTime()),
				type, into);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return new String(formatter.format(ts.getTime()));
	}

    /**
     * 【函数功能】日期大小的比较
     * @param startdate 字符串格式的开始日期
     * @param enddate 字符串格式的日期结束日期
     * @return boolean 如果startdate<enddate则返回true,否则返回false
     */
    public static boolean compareDate(String startdate, String enddate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEF_DATE_FORMAT);
        Date sd = formatter.parse(startdate, new ParsePosition(0));
        Date ed = formatter.parse(enddate, new ParsePosition(0));
        return sd.before(ed);
    }
	
	/**
     * 【函数功能】计算两个时间的相差天数
     * @param time1 时间1(毫秒数)
     * @param time2 时间2(毫秒数)
     * @return int 时间1-时间2=天数
     */
	public static int getIntervalDays(long time1, long time2) {
		return (int) (time1 - time2) / (24 * 60 * 60 * 1000);
	}
    
    /**
     * 【函数功能】计算两个时间的相差天数
     * @param time1 时间1(Date)
     * @param time2 时间2(Date)
     * @return int 时间1-时间2=天数
     */
	public static int getIntervalDays(Date time1, Date time2) {
		if(time1==null||time2==null) return 0;
		return getIntervalDays(time1.getTime(),time2.getTime());
	}
    
    /**
     * 【函数功能】计算两个时间的相差天数
     * @param time1 时间1(Timestamp)
     * @param time2 时间2(Timestamp)
     * @return int 时间1-时间2=天数
     */
	public static int getIntervalDays(Timestamp time1, Timestamp time2) {
		if(time1==null||time2==null) return 0;
		return getIntervalDays(time1.getTime(),time2.getTime());
	}
	
	/**
	 * 【函数功能】日期字符串对象转化为日历对象
	 * @param datestr 日期字符串
	 * @return Calendar日历对象
	 */
	public static Calendar getCalendar(String datestr){
		Date date = parseDate(datestr);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * 【函数功能】获取输入日期_当月的实际天数
	 * @param datestr 日期字符串
	 * @return int 天数
	 */
	public static int getMaxDayOfMonth(String datestr){
		return getCalendar(datestr).getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 【函数功能】获取输入日期_当年的实际天数
	 * @param datestr 日期字符串
	 * @return int 天数
	 */
	public static int getMaxDayOfYear(String datestr){
		return getCalendar(datestr).getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 【函数功能】获取输入日期_当周的第几天
	 * @param datestr 日期字符串
	 * @return int 第几周数
	 */
	public static int getDayOfWeek(String datestr){
		return getCalendar(datestr).get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 【函数功能】获取输入日期_当年的第几天
	 * @param datestr 日期字符串
	 * @return int 第几天数
	 */
	public static int getDayOfYear(String datestr){
		return getCalendar(datestr).get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 【函数功能】获取输入日期_当周的第几天
	 * @param datestr 日期字符串
	 * @return int 第几周数
	 */
	public static int getWeekOfMonth(String datestr){
		return getCalendar(datestr).get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}
	
	/**
	 * 【函数功能】获取输入日期_当年的第几周
	 * @param datestr 日期字符串
	 * @return int 第几周数
	 */
	public static int getWeekOfYear(String datestr){
		return getCalendar(datestr).get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 【函数功能】获取国家区域相关的日期字符串
	 * @param date 日期对象
	 * @param tzone 区域对象
	 * @param locale 国家地方对象
	 * @return String 日期字符串
	 */
    public static String getDate2Tzone(Date date, TimeZone tzone, Locale locale) {
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.SHORT, locale);
		formatter.setTimeZone(tzone);
		return formatter.format(date);
	}
	
	public static void main(String[] args){
//		System.out.println(DateUtils.getCalendar("2007-12-17").get(Calendar.YEAR));
//		System.out.println(DateUtils.getCalendar("2007-12-17").get(Calendar.MONTH));
//		System.out.println(DateUtils.getCalendar("2007-12-17").get(Calendar.DATE));
//		System.out.println(DateUtils.getWeekOfMonth("2007-12-17"));
//		System.out.println(DateUtils.getWeekOfYear("2007-12-17"));
//		System.out.println(DateUtils.getMaxDayOfYear("2008-12-17"));
		System.out.println("123".matches("[0-9]*"));
	}
}

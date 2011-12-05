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
 * ����˵��������һ���������ڲ�������,������һЩ�������ڲ�������
 * @author yehongyu.org
 * @version 1.0 2007-12-3 ����01:30:06
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
     * ����һ������ģʽ����Ʒ�ʽ,�����������Ҫ�ǵõ������ʵ������
     */
    public static DateUtils getInstance() {
        return instance;
    }
    /** 
     * ˽�й��췽������ֹ�ⲿ��������ʵ�� 
     */
    private DateUtils(){}
    
    /**
     * ���������ܡ��ж�������ַ����Ƿ�Ϊ���ڸ�ʽyyyy-MM-dd
     * @param datestr
     * @return true or false
     */
    public static boolean isDate(String datestr) {
		DateFormat parser = new SimpleDateFormat(DEF_DATE_FORMAT);
		try {
			parser.parse(datestr);
		} catch (ParseException e) {
			// e.printStackTrace();
			return false; // ������ܽ������������ַ���
		}
		return true;
	}

    /**
     * ���������ܡ��������ڶ����ȡ�����ִ�
     * @param date ���ڶ���
     * @return String �����ַ���,Ĭ�ϸ�ʽyyyy-MM-dd
     */
    public static String getDateString(Date date) {
    	if(date==null) return "";
        return getDateString(date,DEF_DATE_FORMAT);
    }

    /**
     * ���������ܡ��������ڶ���ָ����ʽ��ȡ�����ִ�
     * @param date ���ڶ���
     * @param pattern ָ����ʽ
     * @return String �����ַ�����ָ����ʽ��
     */
    public static String getDateString(Date date,String pattern) {
        DateFormat parser = new SimpleDateFormat(pattern);
        return parser.format(date);
    }

    /**
     * ���������ܡ��������ַ�����ָ����ʽת��Ϊ���ڶ���
     * @param datastr �����ַ���
     * @param pattern ��ʽ�ִ�
     * @return Date����
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
     * ���������ܡ����ַ���ת��Ϊ����
     * @param datestr String �����ַ���
     * @return Date ת��ʧ�ܷ���Null
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
     * ���������ܡ����ָ����������week����ʼ����
     * @param strDate String ָ�������ڸ�ʽΪyyyy-MM-dd
     * @return String ���ص����ڸ�ʽΪyyyy-MM-dd
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
     * ���������ܡ����ָ����������week����ֹ����
     * @param strDate String ָ�������ڸ�ʽΪyyyy-MM-dd
     * @return String ���ص����ڸ�ʽΪyyyy-MM-dd
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
     * ���������ܡ�Timestamp�����ڵļӼ�
     * @param datestr Timestamp ����Ļ���ʱ��
     * @param type String �Ӽ�������: D ���� M �·� Y �� HH Сʱ MI ���� SS ��
     * @param into int �Ӽ�������
     * @return Timestamp ����ʱ��
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
     * ���������ܡ�String�����ڵļӼ�
     * @param datestr String ����Ļ���ʱ��
     * @param type String �Ӽ�������: D ���� M �·� Y �� HH Сʱ MI ���� SS ��
     * @param into int �Ӽ�������
     * @return String ����ʱ�� yyyy-MM-dd HH:mm:ss
     */
    public static String addDate(String datestr, String type, int into) {
		Timestamp ts = addDate(new Timestamp(parseDate(datestr).getTime()),
				type, into);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return new String(formatter.format(ts.getTime()));
	}

    /**
     * ���������ܡ����ڴ�С�ıȽ�
     * @param startdate �ַ�����ʽ�Ŀ�ʼ����
     * @param enddate �ַ�����ʽ�����ڽ�������
     * @return boolean ���startdate<enddate�򷵻�true,���򷵻�false
     */
    public static boolean compareDate(String startdate, String enddate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEF_DATE_FORMAT);
        Date sd = formatter.parse(startdate, new ParsePosition(0));
        Date ed = formatter.parse(enddate, new ParsePosition(0));
        return sd.before(ed);
    }
	
	/**
     * ���������ܡ���������ʱ����������
     * @param time1 ʱ��1(������)
     * @param time2 ʱ��2(������)
     * @return int ʱ��1-ʱ��2=����
     */
	public static int getIntervalDays(long time1, long time2) {
		return (int) (time1 - time2) / (24 * 60 * 60 * 1000);
	}
    
    /**
     * ���������ܡ���������ʱ����������
     * @param time1 ʱ��1(Date)
     * @param time2 ʱ��2(Date)
     * @return int ʱ��1-ʱ��2=����
     */
	public static int getIntervalDays(Date time1, Date time2) {
		if(time1==null||time2==null) return 0;
		return getIntervalDays(time1.getTime(),time2.getTime());
	}
    
    /**
     * ���������ܡ���������ʱ����������
     * @param time1 ʱ��1(Timestamp)
     * @param time2 ʱ��2(Timestamp)
     * @return int ʱ��1-ʱ��2=����
     */
	public static int getIntervalDays(Timestamp time1, Timestamp time2) {
		if(time1==null||time2==null) return 0;
		return getIntervalDays(time1.getTime(),time2.getTime());
	}
	
	/**
	 * ���������ܡ������ַ�������ת��Ϊ��������
	 * @param datestr �����ַ���
	 * @return Calendar��������
	 */
	public static Calendar getCalendar(String datestr){
		Date date = parseDate(datestr);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * ���������ܡ���ȡ��������_���µ�ʵ������
	 * @param datestr �����ַ���
	 * @return int ����
	 */
	public static int getMaxDayOfMonth(String datestr){
		return getCalendar(datestr).getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * ���������ܡ���ȡ��������_�����ʵ������
	 * @param datestr �����ַ���
	 * @return int ����
	 */
	public static int getMaxDayOfYear(String datestr){
		return getCalendar(datestr).getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * ���������ܡ���ȡ��������_���ܵĵڼ���
	 * @param datestr �����ַ���
	 * @return int �ڼ�����
	 */
	public static int getDayOfWeek(String datestr){
		return getCalendar(datestr).get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * ���������ܡ���ȡ��������_����ĵڼ���
	 * @param datestr �����ַ���
	 * @return int �ڼ�����
	 */
	public static int getDayOfYear(String datestr){
		return getCalendar(datestr).get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * ���������ܡ���ȡ��������_���ܵĵڼ���
	 * @param datestr �����ַ���
	 * @return int �ڼ�����
	 */
	public static int getWeekOfMonth(String datestr){
		return getCalendar(datestr).get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}
	
	/**
	 * ���������ܡ���ȡ��������_����ĵڼ���
	 * @param datestr �����ַ���
	 * @return int �ڼ�����
	 */
	public static int getWeekOfYear(String datestr){
		return getCalendar(datestr).get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * ���������ܡ���ȡ����������ص������ַ���
	 * @param date ���ڶ���
	 * @param tzone �������
	 * @param locale ���ҵط�����
	 * @return String �����ַ���
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

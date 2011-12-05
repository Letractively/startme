/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.math.BigDecimal;

/**
 * 【类说明】通用转换类
 * @author yehongyu.org
 * @version 1.0 2007-12-3 下午01:28:02
 */
public class Convert {
	
	/**
	 * 【函数功能】是否为Null或空字符串
	 * @param s 输入字符串
	 * @return true or false
	 */
    public static boolean isEmpty(String s) {
    	return (s==null||s.trim().equals(""))?true:false;
    }

    /**
     * 【函数功能】返回BigDecimal对象,空则返回new BigDecimal(0).
     * @param s 输入字符串
     * @return 返回BigDecimal.
     */
    public static BigDecimal getBigDecimal(String strN){
    	return getBigDecimal(strN,0);
    }
    
    /**
     * 【函数功能】返回BigDecimal对象,空则返回默认值.
     * @param s 输入字符串
     * @param defval 默认值
     * @return 返回BigDecimal.
     */
    public static BigDecimal getBigDecimal(String s, int defval){
        if (s==null) return new BigDecimal(defval);
        try {
            return new BigDecimal(s);
        }catch (NumberFormatException e) {
            return new BigDecimal(defval);
        }
    }

    /**
     * 【函数功能】获取逻辑值，"1"或"TRUE"为true,"2"或"FALSE"为false.默认为false.
     * @param b 输入字符串
     * @return 逻辑值
     */
    public static boolean getBoolean(String b){
    	return getBoolean(b,false);
    }
    
    /**
     * 【函数功能】获取逻辑值，"1"或"TRUE"为true,"2"或"FALSE"为false.
     * @param b 输入字符串
     * @param defval 默认值
     * @return 逻辑值
     */
    public static boolean getBoolean(String b, boolean defval) {
        if (b == null) return defval;
        String upperB = b.toUpperCase();
        if ("1".equals(b)) return (true);
        if ("0".equals(b)) return (false);
        if ("TRUE".equals(upperB)) return (true);
        if ("FALSE".equals(upperB)) return (false);
        return defval;
    }
    
    /**
     * 【函数功能】获取byte数值，空则返回0。
     * @param strN 输入字符串
     * @return 返回数值
     */
    public static byte getByte(String strN){
    	return getByte(strN,(byte)0);
    }
    
    /**
     * 【函数功能】获取byte数值，空则返回默认数值。
     * @param strN 输入字符串
     * @param defVal 默认数值
     * @return 返回数值
     */
    public static byte getByte(String strN,byte defval){
    	if(isEmpty(strN)) return defval;
    	return strN.getBytes()[0];
    }
    
    /**
     * 【函数功能】获取short数值，空则返回0。
     * @param strN 输入字符串
     * @return 返回数值
     */
    public static short getShort(String strN){
    	return getShort(strN,(short)0);
    }
    
    /**
     * 【函数功能】获取short数值，空则返回默认数值。
     * @param strN 输入字符串
     * @param defVal 默认数值
     * @return 返回数值
     */
    public static short getShort(String strN, short defval) {
        if(isEmpty(strN)) return defval;
        try {
            return Short.parseShort(strN);
        }catch (NumberFormatException e) {
            return defval;
        }
    }
    
    /**
     * 【函数功能】获取int数值，空则返回0。
     * @param strN 输入字符串
     * @return 返回数值
     */
    public static int getInt(String strN){
    	return getInt(strN,0);
    }

    /**
     * 【函数功能】获取int数值，空则返回默认数值。
     * @param strN 输入字符串
     * @param defVal 默认数值
     * @return 返回数值
     */
    public static int getInt(String strN, int defVal) {
        if (isEmpty(strN)) return defVal;
        try {
            return Integer.parseInt(strN);
        }catch (NumberFormatException e) {
            return defVal;
        }
    }

    /**
     * 【函数功能】获取long数值，空则返回0。
     * @param strN 输入字符串
     * @return 返回数值
     */
    public static float getFloat(String strN) {
    	return getFloat(strN,0f);
    }

    /**
     * 【函数功能】获取float数值，空则返回默认数值。
     * @param strN 输入字符串
     * @param defVal 默认数值
     * @return 返回数值
     */
    public static float getFloat(String strN, float defVal) {
        if (isEmpty(strN)) return defVal;
        try {
            return Float.parseFloat(strN);
        }catch (NumberFormatException e) {
            return defVal;
        }
    }

    /**
     * 【函数功能】获取long数值，空则返回0。
     * @param strN 输入字符串
     * @return 返回数值
     */
    public static long getLong(String strN){
    	return getLong(strN,0L);
    }
    
    /**
     * 【函数功能】获取long数值，空则返回默认数值。
     * @param strN 输入字符串
     * @param defVal 默认数值
     * @return 返回数值
     */
    public static long getLong(String strN, long defVal) {
        if (isEmpty(strN)) return defVal;
        try {
            return Long.parseLong(strN);
        }catch (NumberFormatException e) {
        	return defVal;
        }
    }
    
    /**
     * 【函数功能】获取double数值，空则返回0。
     * @param strN 输入字符串
     * @return 返回数值
     */
    public static double getDouble(String strN){
    	return getDouble(strN,0d);
    }
    
    /**
     * 【函数功能】获取double数值，空则返回默认数值。
     * @param strN 输入字符串
     * @param defVal 默认数值
     * @return 返回数值
     */
    public static double getDouble(String strN, double defVal) {
        if (isEmpty(strN)) return defVal;
        try {
            return Double.parseDouble(strN);
        }catch (NumberFormatException e) {
        	return defVal;
        }
    }

    /**
     * 【函数功能】获取对象的字符串表示，Null则返回空串
     * @param o 输入对象
     * @return String 输出字符串
     */
    public static String getString(Object o) {
    	return o!=null?o.toString().trim():"";
    }

    /**
     * 【函数功能】获取输入字符串对象的值，隐藏了空值和空串。
     * @param s 输入字符串对象
     * @return String 处理后的字符串
     */
    public static String getString(String s) {
        return getString(s, "");
    }
    
    /**
     * 【函数功能】获取输入字符串对象的值，如果为空则返回默认对象
     * @param s 输入字符串对象
     * @param defval 默认返回字符串对象
     * @return String 输出的字符串
     */
    public static String getString(String s, String defval) {
    	return isEmpty(s)?defval:s.trim();
    }

    /**
	 * 【函数功能】main方法
	 * 
	 * @param args
	 */
    public static void main(String[] args){
    	System.out.println(Convert.getLong(null));
    }
}

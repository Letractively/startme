package com.yehongyu.mansys.dao.validate;

/**
 * DAO验证基类
 * @author yingyang
 * @since 2011-11-11
 */
public class BaseValidate {
	
	/**
	 * 验证字段不为空
	 * @param object
	 * @param sb
	 * @param tipName
	 * @return
	 */
	protected static StringBuffer checkNotNull(Object object,StringBuffer sb,String tipName){
		if(sb==null) sb = new StringBuffer();
		if(object==null)sb.append("\n").append(":").append(tipName).append(" is must not be null");
		return sb;
	}
	
	/**
	 * 验证字段不超过指定长度
	 * @param str
	 * @param maxLength
	 * @param sb
	 * @param tipName
	 * @return
	 */
	protected static StringBuffer checkLength(String str,int maxLength,StringBuffer sb,String tipName){
		if(sb==null) sb = new StringBuffer();
		if(str==null) return sb;
		if(str.getBytes().length>maxLength){
			sb.append("\n").append(":").append(tipName).append(" over the length of ").append(maxLength);
		}
		return sb;
	}

}

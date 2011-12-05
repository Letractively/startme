/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.util.Map;

/**
 * 【类说明】应用级别的异常
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:02:55
 */
public class MyException extends Exception {

	/** MMT的错误编码 */
	private String errorcode;

	/** 错误出现时的上下文 */
	private Map context;

	/** 是否被记录的标志0表示未被标记 */
	private int flag = 0;

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 *            错误编码
	 */
	public MyException(String errorCode) {
		this.errorcode = errorCode;
	}

	/**
	 * 
	 * @param errorCode
	 * @param ex
	 */
	public MyException(String errorCode, Throwable ex) {
		super("", ex);
		this.errorcode = errorCode;
	}

	/**
	 * 
	 * @param errorCode
	 * 
	 * @param context
	 * @param ex
	 */
	public MyException(String errorCode, Map context, Throwable ex) {
		super("", ex);
		this.errorcode = errorCode;

		this.context = context;

	}

	/**
	 * @return Returns the context.
	 */
	public Map getContext() {
		return context;
	}

	/**
	 * @param context
	 *            The context to set.
	 */
	public void setContext(Map context) {
		this.context = context;
	}

	/**
	 * @return Returns the errorcode.
	 */
	public String getErrorcode() {
		return errorcode;
	}

	/**
	 * @return Returns the flag.
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

}

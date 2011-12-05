/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.util.Map;

/**
 * ����˵����Ӧ�ü�����쳣
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:02:55
 */
public class MyException extends Exception {

	/** MMT�Ĵ������ */
	private String errorcode;

	/** �������ʱ�������� */
	private Map context;

	/** �Ƿ񱻼�¼�ı�־0��ʾδ����� */
	private int flag = 0;

	/**
	 * ���캯��
	 * 
	 * @param errorCode
	 *            �������
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

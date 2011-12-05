/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ����˵�������������ʵ�ֽӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:17:54
 */
public class MyBusinessInvocationHandler implements InvocationHandler {
	/** ������ʵ�� */
	private MyBaseBusiness businessImpl;
	
	/**
	 * ���ݴ��������ʵ�����嵱ǰ����ʵ������Ķ���
	 * @param businessImpl ������ʵ��
	 */
	public MyBusinessInvocationHandler(MyBaseBusiness businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	/**
	 * ���������
	 * @param obj ����ʵ��
	 * @param method ���÷�������
	 * @param args ���÷�������
	 * @return ���÷��ؽ��
	 * @throws Throwable ����ʱ�����쳣
	 */
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		Object ret;
		
		try {
			ret = method.invoke(businessImpl, args);
			MySessionFactory.commitAllTransaction();
		} catch (Throwable e) {
			MySessionFactory.rollbackAllTransaction();
			throw e.getCause();
		} finally {
			MySessionFactory.releaseAllSession();
		}
		
		return ret;
	}
}

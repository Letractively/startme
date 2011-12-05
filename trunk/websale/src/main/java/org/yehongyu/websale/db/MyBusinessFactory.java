/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.turbine.Turbine;
import org.yehongyu.websale.common.util.ConfigUtil;
import org.yehongyu.websale.common.util.MyException;

/**
 * ����˵��������������Ĺ���
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:17:43
 */
public abstract class MyBusinessFactory {
	/** ��־���� */
	private static final Logger log = Logger.getLogger(MyBusinessFactory.class);
	/** �Ѵ����Ĺ�����ʵ�� */
	private static final HashMap<String, Object> hmBm = new HashMap<String, Object>();
	
	/**
	 * ȡ�ù�����ʵ��
	 * @param name ��������������
	 * @return ������ʵ��
	 * @throws MyException ȡ�ù�����ʵ��ʱ�����쳣
	 */
	public static Object getManager(String name) throws MyException {
		Object manager = hmBm.get(name);
		if (manager == null) {
			manager = createManager(name);
		}

		return manager;
	}
	
	/**
	 * ȡ�ù�����ʵ����ʵ�ֵĽӿ�
	 * @param manager ������ʵ��
	 * @return ��������ʵ�ֵĽӿ�
	 * @throws MyException ������û��ʵ�ֽӿ�
	 */
	private static Class getInterfaceClass(MyBaseBusiness manager) throws MyException {
		Class[] cls = manager.getClass().getInterfaces();
		if (cls == null || cls.length == 0)
			throw new MyException("ERROR CODE");
		return cls[0];
	}
	
	/**
	 * ����������ʵ��
	 * @param name ��������������
	 * @return ������ʵ��
	 * @throws MyException ����������ʱ�����쳣
	 */
	private static synchronized Object createManager(String name) throws MyException {
		Object proxyManager;
		try {
			//ȡ������������·��
			String className = ConfigUtil.getConfigByRealPath(Turbine.getRealPath("WEB-INF/conf/AppConfig.properties")).getPropValue(name);
			Constructor c = Class.forName(className).getDeclaredConstructor(new Class[0]);
			c.setAccessible(true);
			proxyManager = c.newInstance(new Object[0]);
			MyBaseBusiness manager = (MyBaseBusiness)c.newInstance(new Object[0]);
			
			Class clsInterface = getInterfaceClass(manager);
			
			InvocationHandler handler = new MyBusinessInvocationHandler(manager);
			proxyManager = Proxy.newProxyInstance(
					clsInterface.getClassLoader(),
	                new Class[]{clsInterface},
	                handler);
			hmBm.put(name, proxyManager);
			
		} catch (Exception e) {
			log.error("�޷�����������ʵ��[name = " + name + "]", e);
			throw new MyException("ERROR CODE", e);
		}
		
		return proxyManager;
	}
}
